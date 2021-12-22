package com.mclowicz.mcmovie.data.repository

import com.mclowicz.mcmovie.data.network.MovieApiService
import javax.inject.Inject

class MovieRepository @Inject constructor(
    val api: MovieApiService
) {

    fun getMostPopularMovies() = api.getMostPopularMovies()

    fun getTopRatedMovies() = api.getTopRatedMovies()

    fun getMostPopularTvShows() = api.getMostPopularTvShows()

    fun getPopularPeoples() = api.getPopularPeoples()

    fun getMovieDetail(id: Int) = api.getMovieDetail(id)

    fun getTvDetail(id: Int) = api.getTvDetail(id)

    fun getPersonDetail(id: Int) = api.getPersonDetail(id)
}