package com.mclowicz.mcmovie.features.home.ui

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.*
import com.mclowicz.mcmovie.data.repository.MovieRepository
import com.mclowicz.mcmovie.features.base.BaseViewModel
import com.mclowicz.mcmovie.features.common.MoreEvent
import com.mclowicz.mcmovie.features.common.MoreType
import com.mclowicz.mcmovie.features.navigation.NavEvent
import com.mclowicz.mcmovie.features.detail.components.DetailComponentType
import com.mclowicz.mcmovie.features.home.components.HomeComponent
import com.mclowicz.mcmovie.features.home.components.HomeScreenComposer
import com.mclowicz.mcmovie.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: MovieRepository
) : BaseViewModel() {

    val homeComponents = ObservableArrayList<HomeComponent>()

    private val navDetailEvent = SingleLiveEvent<NavEvent>()

    fun getNavDetailEvent(): LiveData<NavEvent> = navDetailEvent

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            combine(
                request(repository.getMostPopularMovies()),
                request(repository.getTopRatedMovies()),
                request(repository.getMostPopularTvShows()),
                request(repository.getPopularPeoples())
            ) { moviesResource, topRatedResource, tvResource, peoplesResource ->
                HomeScreenComposer.compose(
                    moviesResource,
                    topRatedResource,
                    tvResource,
                    peoplesResource
                )
                    .also {
                        with(homeComponents) {
                            clear()
                            addAll(it)
                        }
                    }
            }
                .collect { }
        }
    }

    fun refreshData() {
        fetchData()
    }

    fun onItemClicked(id: Int, eventType: DetailComponentType) {
        navDetailEvent.value = NavEvent.NavDetailEvent(id, eventType)
    }

    fun onMoreClicked(title: String, moreType: MoreType) {
        navDetailEvent.value = NavEvent.NavMoreEvent(MoreEvent(title, moreType))
    }
}