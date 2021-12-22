package com.mclowicz.mcmovie.features.detail.components

import androidx.lifecycle.MutableLiveData
import com.mclowicz.mcmovie.data.model.movie.MovieDetailResult
import com.mclowicz.mcmovie.data.model.person.PopularPersonDetail
import com.mclowicz.mcmovie.data.repository.MovieRepository
import com.mclowicz.mcmovie.util.Resource

class PersonComponent(
    override val repository: MovieRepository,
    override val id: Int
) : DetailComponent(repository, id) {

    override val data = MutableLiveData<Resource<PopularPersonDetail>>()

    override val loadingState = Resource.loading(null)
    override val errorState: Resource<PopularPersonDetail> = Resource.loading(null)

    init {
        fetchData(id)
    }

    override fun fetchData(id: Int) {
        emitRequest(
            repository.getPersonDetail(id),
            loadingState,
            errorState,
            data
        )
    }
}