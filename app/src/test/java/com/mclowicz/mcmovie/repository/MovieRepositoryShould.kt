package com.mclowicz.mcmovie.repository

import com.mclowicz.mcmovie.data.model.movie.MovieDetailResult
import com.mclowicz.mcmovie.data.model.movie.PopularResult
import com.mclowicz.mcmovie.data.model.person.PopularPeopleResult
import com.mclowicz.mcmovie.data.model.person.PopularPersonDetail
import com.mclowicz.mcmovie.data.model.tv.PopularTvDetail
import com.mclowicz.mcmovie.data.model.tv.PopularTvResult
import com.mclowicz.mcmovie.data.network.MovieApiService
import com.mclowicz.mcmovie.data.repository.MovieRepository
import com.mclowicz.mcmovie.util.Resource
import com.mclowicz.mcmovie.utils.BaseUnitTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

@ExperimentalCoroutinesApi
class MovieRepositoryShould : BaseUnitTest() {

    private val remoteService: MovieApiService = mock()
    private val popularResult = mock<PopularResult>()
    private val popularTvResult = mock<PopularTvResult>()
    private val popularPeopleResult = mock<PopularPeopleResult>()
    private val movieDetailResult = mock<MovieDetailResult>()
    private val popularTvDetail = mock<PopularTvDetail>()
    private val popularPersonDetail = mock<PopularPersonDetail>()

    @Test
    fun propagateErrors() = runBlockingTest {

        val repository = mockFailureCase()

        assertEquals(
            networkError,
            repository.getMostPopularMovies().first()
        )
        assertEquals(
            networkError,
            repository.getTopRatedMovies().first()
        )
        assertEquals(
            networkError,
            repository.getMostPopularTvShows().first()
        )
        assertEquals(
            networkError,
            repository.getPopularPeoples().first()
        )
        assertEquals(
            networkError,
            repository.getMovieDetail(id).first()
        )
        assertEquals(
            networkError,
            repository.getTvDetail(id).first()
        )
        assertEquals(
            networkError,
            repository.getPersonDetail(id).first()
        )
    }

    @Test
    fun getMostPopularMoviesFromService() = runBlockingTest {
        val repository = MovieRepository(remoteService)
        repository.getMostPopularMovies()
        verify(remoteService, times(1)).getMostPopularMovies()
    }

    @Test
    fun emitMostPopularMoviesFromService() = runBlockingTest {
        val repository = mockSuccessfulCase()

        assertEquals(popularResult, repository.getMostPopularMovies().first().data)
    }

    @Test
    fun getTopRatedMoviesFromService() = runBlockingTest {
        val repository = MovieRepository(remoteService)
        repository.getTopRatedMovies()
        verify(remoteService, times(1)).getTopRatedMovies()
    }

    @Test
    fun emitTopRatedMoviesFromService() = runBlockingTest {
        val repository = mockSuccessfulCase()

        assertEquals(popularResult, repository.getTopRatedMovies().first().data)
    }

    @Test
    fun getMostPopularTvShowsFromService() = runBlockingTest {
        val repository = MovieRepository(remoteService)
        repository.getMostPopularTvShows()
        verify(remoteService, times(1)).getMostPopularTvShows()
    }

    @Test
    fun emitMostPopularTvShowsFromService() = runBlockingTest {
        val repository = mockSuccessfulCase()

        assertEquals(popularTvResult, repository.getMostPopularTvShows().first().data)
    }

    @Test
    fun getPopularPeoplesFromService() = runBlockingTest {
        val repository = MovieRepository(remoteService)
        repository.getPopularPeoples()
        verify(remoteService, times(1)).getPopularPeoples()
    }

    @Test
    fun emitPopularPeoplesFromService() = runBlockingTest {
        val repository = mockSuccessfulCase()

        assertEquals(popularPeopleResult, repository.getPopularPeoples().first().data)
    }

    @Test
    fun getMovieDetailFromService() = runBlockingTest {
        val repository = MovieRepository(remoteService)
        repository.getMovieDetail(id)
        verify(remoteService, times(1)).getMovieDetail(id)
    }

    @Test
    fun emitMovieDetailFromService() = runBlockingTest {
        val repository = mockSuccessfulCase()

        assertEquals(movieDetailResult, repository.getMovieDetail(id).first().data)
    }

    @Test
    fun getTvDetailFromService() = runBlockingTest {
        val repository = MovieRepository(remoteService)
        repository.getTvDetail(id)
        verify(remoteService, times(1)).getTvDetail(id)
    }

    @Test
    fun emitTvDetailFromService() = runBlockingTest {
        val repository = mockSuccessfulCase()

        assertEquals(popularTvDetail, repository.getTvDetail(id).first().data)
    }

    @Test
    fun getPersonDetailFromService() = runBlockingTest {
        val repository = MovieRepository(remoteService)
        repository.getPersonDetail(id)
        verify(remoteService, times(1)).getPersonDetail(id)
    }

    @Test
    fun emitPersonDetailFromService() = runBlockingTest {
        val repository = mockSuccessfulCase()

        assertEquals(popularPersonDetail, repository.getPersonDetail(id).first().data)
    }

    private suspend fun mockFailureCase(): MovieRepository {
        whenever(remoteService.getMostPopularMovies()).thenReturn(
            flow {
                emit(networkError)
            }
        )
        whenever(remoteService.getTopRatedMovies()).thenReturn(
            flow {
                emit(networkError)
            }
        )
        whenever(remoteService.getMostPopularTvShows()).thenReturn(
            flow {
                emit(networkError)
            }
        )
        whenever(remoteService.getPopularPeoples()).thenReturn(
            flow {
                emit(networkError)
            }
        )
        whenever(remoteService.getMovieDetail(id)).thenReturn(
            flow {
                emit(networkError)
            }
        )
        whenever(remoteService.getTvDetail(id)).thenReturn(
            flow {
                emit(networkError)
            }
        )
        whenever(remoteService.getPersonDetail(id)).thenReturn(
            flow {
                emit(networkError)
            }
        )
        return MovieRepository(remoteService)
    }

    private suspend fun mockSuccessfulCase(): MovieRepository {
        whenever(remoteService.getMostPopularMovies()).thenReturn(
            flow {
                emit(Resource.success(popularResult))
            }
        )
        whenever(remoteService.getTopRatedMovies()).thenReturn(
            flow {
                emit(Resource.success(popularResult))
            }
        )
        whenever(remoteService.getMostPopularTvShows()).thenReturn(
            flow {
                emit(Resource.success(popularTvResult))
            }
        )
        whenever(remoteService.getPopularPeoples()).thenReturn(
            flow {
                emit(Resource.success(popularPeopleResult))
            }
        )
        whenever(remoteService.getMovieDetail(id)).thenReturn(
            flow {
                emit(Resource.success(movieDetailResult))
            }
        )
        whenever(remoteService.getTvDetail(id)).thenReturn(
            flow {
                emit(Resource.success(popularTvDetail))
            }
        )
        whenever(remoteService.getPersonDetail(id)).thenReturn(
            flow {
                emit(Resource.success(popularPersonDetail))
            }
        )
        return MovieRepository(remoteService)
    }
}