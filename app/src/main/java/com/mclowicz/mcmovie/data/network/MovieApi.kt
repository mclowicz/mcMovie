package com.mclowicz.mcmovie.data.network

import com.mclowicz.mcmovie.data.model.movie.MovieDetailResult
import com.mclowicz.mcmovie.data.model.movie.PopularResult
import com.mclowicz.mcmovie.data.model.person.PopularPeopleResult
import com.mclowicz.mcmovie.data.model.person.PopularPersonDetail
import com.mclowicz.mcmovie.data.model.tv.PopularTvDetail
import com.mclowicz.mcmovie.data.model.tv.PopularTvResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApi {

    @GET("movie/popular")
    suspend fun getMostPopularMovies(): Response<PopularResult>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(): Response<PopularResult>

    @GET("tv/popular")
    suspend fun getMostPopularTvShows(): Response<PopularTvResult>

    @GET("person/popular")
    suspend fun getPopularPeoples(): Response<PopularPeopleResult>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") id: Int
    ): Response<MovieDetailResult>

    @GET("tv/{tv_id}")
    suspend fun getTvDetail(
        @Path("tv_id") id: Int
    ): Response<PopularTvDetail>

    @GET("person/{person_id}")
    suspend fun getPersonDetail(
        @Path("person_id") id: Int
    ): Response<PopularPersonDetail>
}