package com.mclowicz.mcmovie.features.more.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mclowicz.mcmovie.data.network.MovieApi
import com.mclowicz.mcmovie.features.common.MoreType
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

private const val TMDB_STARTING_PAGE_INDEX = 1

class MoviesPagingSource @Inject constructor(
    private val movieApi: MovieApi,
    private val moreEventType: MoreType
) : PagingSource<Int, MoreResponse>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MoreResponse> {
        val pageIndex = params.key ?: TMDB_STARTING_PAGE_INDEX
        return try {
            val movies = mutableListOf<MoreResponse>()
            when (moreEventType) {
                MoreType.POPULAR_MOVIES -> {
                    val response = movieApi.getMostPopularMovies(page = pageIndex)
                    response.body()?.let { movies.addAll(it.results) }
                }
                MoreType.TOP_RATED_MOVIES -> {
                    val response = movieApi.getTopRatedMovies(page = pageIndex)
                    response.body()?.let { movies.addAll(it.results) }
                }
                MoreType.POPULAR_TV_SHOWS -> {
                    val response = movieApi.getMostPopularTvShows(page = pageIndex)
                    response.body()?.let { movies.addAll(it.results) }
                }
                MoreType.POPULAR_PEOPLES -> {
                    val response = movieApi.getPopularPeoples(page = pageIndex)
                    response.body()?.let { movies.addAll(it.results) }
                }
                else -> return LoadResult.Error(IOException(""))
            }
            val nextKey =
                if (movies.isEmpty()) {
                    null
                } else {
                    pageIndex + (params.loadSize / 25)
                }
            LoadResult.Page(
                data = movies,
                prevKey = if (pageIndex == TMDB_STARTING_PAGE_INDEX) null else pageIndex,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MoreResponse>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}