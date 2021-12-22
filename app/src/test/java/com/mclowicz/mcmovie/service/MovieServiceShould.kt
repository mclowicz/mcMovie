package com.mclowicz.mcmovie.service

import com.mclowicz.mcmovie.data.model.movie.MovieDetailResult
import com.mclowicz.mcmovie.data.model.movie.PopularResult
import com.mclowicz.mcmovie.data.model.person.PopularPeopleResult
import com.mclowicz.mcmovie.data.model.person.PopularPersonDetail
import com.mclowicz.mcmovie.data.model.tv.PopularTvDetail
import com.mclowicz.mcmovie.data.model.tv.PopularTvResult
import com.mclowicz.mcmovie.data.network.MovieApi
import com.mclowicz.mcmovie.data.network.MovieApiService
import com.mclowicz.mcmovie.util.Resource
import com.mclowicz.mcmovie.utils.BaseUnitTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.runBlockingTest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Test
import retrofit2.Response
import java.lang.Error

@ExperimentalCoroutinesApi
class MovieServiceShould : BaseUnitTest() {

    private val api: MovieApi = mock()
    private lateinit var service: MovieApiService
    private lateinit var responseError: Response<Any>
    private lateinit var resourceError: Resource<Error>
    private lateinit var popularResult: PopularResult
    private lateinit var popularTvResult: PopularTvResult
    private lateinit var popularPeopleResult: PopularPeopleResult
    private lateinit var movieDetailResult: MovieDetailResult
    private lateinit var popularTvDetail: PopularTvDetail
    private lateinit var popularPersonDetail: PopularPersonDetail

    @Before
    fun setup() {
        responseError = Response.error(
            500,
            "{\"key\":[\"Error\"]}"
                .toResponseBody("application/json".toMediaTypeOrNull())
        )
        resourceError = Resource.error("Error", null)
        popularResult = PopularResult(page = 0, results = listOf(), totalPages = 100)
        popularTvResult = PopularTvResult(page = 0, results = listOf(), totalPages = 100)
        popularPeopleResult = PopularPeopleResult(page = 0, results = listOf(), totalPages = 100)
        movieDetailResult = MovieDetailResult(
            id = 0,
            title = "Title",
            overview = "Lorem ipsum",
            popularity = 0.1,
            posterPath = "http://",
            backdropPath = null,
            releaseDate = "01.01.2022",
            revenue = 100,
            voteAverage = 10.0,
            voteCount = 100,
            budget = 9
        )
        popularTvDetail = PopularTvDetail(
            id = 0,
            backdropPath = null,
            name = "Lorem ipsum",
            overview = "",
            popularity = 1.1,
            posterPath = "",
            voteAverage = 1.2,
            voteCount = 1,
            numberOfEpisodes = 1,
            numberOfSeasons = 1,
            firstAirDate = "01.01.2022"
        )
        popularPersonDetail = PopularPersonDetail(
            id = 0,
            name = "Lorem ipsum",
            popularity = 0.1,
            adult = true,
            birthday = "01.01.2022",
            biography = "",
            placeOfBirth = "",
            profilePath = "http://"
        )
    }

    @Test
    fun emitsErrorResultsWhenNetworkFails() = runBlockingTest {

        mockFailureCase()

        assertEquals(
            resourceError,
            service.getMostPopularMovies().first()
        )
        assertEquals(
            resourceError,
            service.getTopRatedMovies().first()
        )
        assertEquals(
            resourceError,
            service.getMostPopularTvShows().first()
        )
        assertEquals(
            resourceError,
            service.getPopularPeoples().first()
        )
        assertEquals(
            resourceError,
            service.getMovieDetail(id).first()
        )
        assertEquals(
            resourceError,
            service.getTvDetail(id).first()
        )
        assertEquals(
            resourceError,
            service.getPersonDetail(id).first()
        )
    }

    @Test
    fun convertResponseValuesToFlowResourcesAndEmitThem() = runBlockingTest {

        mockSuccessfulCase()

        assertEquals(
            Resource.success(popularResult),
            service.getMostPopularMovies().first()
        )
        assertEquals(
            Resource.success(popularResult),
            service.getMostPopularMovies().first()
        )
        assertEquals(
            Resource.success(popularTvResult),
            service.getMostPopularTvShows().first()
        )
        assertEquals(
            Resource.success(popularPeopleResult),
            service.getPopularPeoples().first()
        )
        assertEquals(
            Resource.success(movieDetailResult),
            service.getMovieDetail(id).first()
        )
        assertEquals(
            Resource.success(popularTvDetail),
            service.getTvDetail(id).first()
        )
        assertEquals(
            Resource.success(popularPersonDetail),
            service.getPersonDetail(id).first()
        )
    }

    @Test
    fun fetchResourcesFromApi() = runBlockingTest {

        mockSuccessfulCase()

        service.apply {
            getMostPopularMovies().single()
            getTopRatedMovies().single()
            getMostPopularTvShows().single()
            getPopularPeoples().single()
            getMovieDetail(id).single()
            getTvDetail(id).single()
            getPersonDetail(id).single()
        }

        verify(api, times(1)).getMostPopularMovies()
        verify(api, times(1)).getTopRatedMovies()
        verify(api, times(1)).getMostPopularTvShows()
        verify(api, times(1)).getPopularPeoples()
        verify(api, times(1)).getMovieDetail(id)
        verify(api, times(1)).getTvDetail(id)
        verify(api, times(1)).getPersonDetail(id)
    }

    private suspend fun mockFailureCase() {
        whenever(api.getMostPopularMovies()).thenReturn(responseError as Response<PopularResult>)
        whenever(api.getTopRatedMovies()).thenReturn(responseError as Response<PopularResult>)
        whenever(api.getMostPopularTvShows()).thenReturn(responseError as Response<PopularTvResult>)
        whenever(api.getPopularPeoples()).thenReturn(responseError as Response<PopularPeopleResult>)
        whenever(api.getMovieDetail(id)).thenReturn(responseError as Response<MovieDetailResult>)
        whenever(api.getTvDetail(id)).thenReturn(responseError as Response<PopularTvDetail>)
        whenever(api.getPersonDetail(id)).thenReturn(responseError as Response<PopularPersonDetail>)
        service = MovieApiService(api)
    }

    private suspend fun mockSuccessfulCase() {
        whenever(api.getMostPopularMovies()).thenReturn(Response.success(popularResult))
        whenever(api.getTopRatedMovies()).thenReturn(Response.success(popularResult))
        whenever(api.getMostPopularTvShows()).thenReturn(Response.success(popularTvResult))
        whenever(api.getPopularPeoples()).thenReturn(Response.success(popularPeopleResult))
        whenever(api.getMovieDetail(id)).thenReturn(Response.success(movieDetailResult))
        whenever(api.getTvDetail(id)).thenReturn(Response.success(popularTvDetail))
        whenever(api.getPersonDetail(id)).thenReturn(Response.success(popularPersonDetail))
        service = MovieApiService(api)
    }
}