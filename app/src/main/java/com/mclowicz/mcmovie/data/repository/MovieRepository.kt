package com.mclowicz.mcmovie.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mclowicz.mcmovie.data.network.MovieApi
import com.mclowicz.mcmovie.data.network.MovieApiService
import com.mclowicz.mcmovie.features.common.MoreType
import com.mclowicz.mcmovie.features.more.paging.MoreResponse
import com.mclowicz.mcmovie.features.more.paging.MoviesPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val apiService: MovieApiService,
    private val api: MovieApi
) {

    fun getMostPopularMovies() = apiService.getMostPopularMovies()

    fun getTopRatedMovies() = apiService.getTopRatedMovies()

    fun getMostPopularTvShows() = apiService.getMostPopularTvShows()

    fun getPopularPeoples() = apiService.getPopularPeoples()

    fun getMovieDetail(id: Int) = apiService.getMovieDetail(id)

    fun getTvDetail(id: Int) = apiService.getTvDetail(id)

    fun getPersonDetail(id: Int) = apiService.getPersonDetail(id)

    fun getPaging(moreEventType: MoreType): Flow<PagingData<MoreResponse>> {
        return Pager(
            config = PagingConfig(
                pageSize = 25,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                MoviesPagingSource(
                    movieApi = api,
                    moreEventType = moreEventType
                )
            }
        ).flow
    }
}