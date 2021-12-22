package com.mclowicz.mcmovie.data.network

import com.mclowicz.mcmovie.util.Util.toResource
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieApiService @Inject constructor(
    private val api: MovieApi
) {

    fun getMostPopularMovies() = flow {
        emit(toResource(response = api.getMostPopularMovies()))
    }

    fun getTopRatedMovies() = flow {
        emit(toResource(response = api.getTopRatedMovies()))
    }

    fun getMostPopularTvShows() = flow {
        emit(toResource(response = api.getMostPopularTvShows()))
    }

    fun getPopularPeoples() = flow {
        emit(toResource(response = api.getPopularPeoples()))
    }

    fun getMovieDetail(id: Int) = flow {
        emit(toResource(response = api.getMovieDetail(id)))
    }

    fun getTvDetail(id: Int) = flow {
        emit(toResource(response = api.getTvDetail(id)))
    }

    fun getPersonDetail(id: Int) = flow {
        emit(toResource(response = api.getPersonDetail(id)))
    }
}