package com.mclowicz.mcmovie.features.home.components

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import com.mclowicz.mcmovie.data.model.movie.PopularMovie
import com.mclowicz.mcmovie.data.model.movie.PopularResult
import com.mclowicz.mcmovie.features.common.MoreType
import com.mclowicz.mcmovie.util.Resource

class MostPopularMoviesComponent(
    override val data: Resource<PopularResult>
) : HomeComponent(data) {

    override var title: MutableLiveData<String?> = MutableLiveData(null)
    override val items: ObservableArrayList<PopularMovie> = ObservableArrayList()
    override var moreType: MoreType = MoreType.POPULAR_MOVIES

    init {
        refreshData()
    }

    private fun refreshData() {
        data.data?.results?.let {
            items.apply {
                clear()
                addAll(it)
            }
        }
    }

    override fun setTitle(title: String?) {
        this.title.value = title
    }

    override fun addMoreData() {
        data.data?.results?.let {
            items.addAll(it)
        }
    }
}