package com.mclowicz.mcmovie.features.more

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.mclowicz.mcmovie.data.repository.MovieRepository
import com.mclowicz.mcmovie.features.base.BaseViewModel
import com.mclowicz.mcmovie.features.common.MoreType
import com.mclowicz.mcmovie.features.detail.components.DetailComponentType
import com.mclowicz.mcmovie.features.more.paging.MoreResponse
import com.mclowicz.mcmovie.features.navigation.NavEvent
import com.mclowicz.mcmovie.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MoreViewModel @Inject constructor(
    private val repository: MovieRepository
) : BaseViewModel() {

    private val navDetailEvent = SingleLiveEvent<NavEvent>()

    fun getNavDetailEvent(): LiveData<NavEvent> = navDetailEvent

    fun getPaging(moreEventType: MoreType): Flow<PagingData<MoreResponse>> {
        return repository.getPaging(moreEventType).cachedIn(viewModelScope)
    }

    fun onItemClicked(itemId: Int, eventType: DetailComponentType) {
        navDetailEvent.value = NavEvent.NavDetailEvent(itemId, eventType)
    }
}