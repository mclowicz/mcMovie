package com.mclowicz.mcmovie.features.detail.components

import androidx.lifecycle.MutableLiveData
import com.mclowicz.mcmovie.data.model.movie.MovieDetailResult
import com.mclowicz.mcmovie.data.repository.MovieRepository
import com.mclowicz.mcmovie.util.Resource

class MovieComponent(
    override val repository: MovieRepository,
    override val id: Int
) : DetailComponent(repository, id) {

    override val data = MutableLiveData<Resource<MovieDetailResult>>()

    override val loadingState = Resource.loading(null)
    override val errorState: Resource<MovieDetailResult> = Resource.loading(null)

    init {
        fetchData(id)
    }

    override fun fetchData(id: Int) {
        emitRequest(
            repository.getMovieDetail(id),
            loadingState,
            errorState,
            data
        )
    }
}