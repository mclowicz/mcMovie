package com.mclowicz.mcmovie.features.detail.components

import androidx.lifecycle.MutableLiveData
import com.mclowicz.mcmovie.data.model.tv.PopularTvDetail
import com.mclowicz.mcmovie.data.repository.MovieRepository
import com.mclowicz.mcmovie.util.Resource

class TvComponent(
    override val repository: MovieRepository,
    override val id: Int
) : DetailComponent(repository, id) {

    override val data = MutableLiveData<Resource<PopularTvDetail>>()

    override val loadingState = Resource.loading(null)
    override val errorState: Resource<PopularTvDetail> = Resource.loading(null)

    init {
        fetchData(id)
    }

    override fun fetchData(id: Int) {
        emitRequest(
            repository.getTvDetail(id),
            loadingState,
            errorState,
            data
        )
    }
}