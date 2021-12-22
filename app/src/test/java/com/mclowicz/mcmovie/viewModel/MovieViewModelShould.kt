package com.mclowicz.mcmovie.viewModel

import com.mclowicz.mcmovie.data.model.movie.PopularResult
import com.mclowicz.mcmovie.data.model.person.PopularPeopleResult
import com.mclowicz.mcmovie.data.model.tv.PopularTvResult
import com.mclowicz.mcmovie.features.home.ui.HomeViewModel
import com.mclowicz.mcmovie.util.Resource
import com.mclowicz.mcmovie.utils.BaseUnitTest
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

@ExperimentalCoroutinesApi
class MovieViewModelShould : BaseUnitTest() {

    private val loadingState = Resource.loading(null)
    private val errorState = Resource.error("Error", null)
    private val popularResult = PopularResult(0, listOf(), 100)
    private val popularTvResult = PopularTvResult(0, listOf(), 100)
    private val popularPeopleResult = PopularPeopleResult(0, listOf(), 100)
    private val fetchInvocationCount = 1
    private val COMPONENT_INDEX_MOST_POPULAR_MOVIES = 1
    private val COMPONENT_INDEX_TOP_RATED_MOVIES = 2
    private val COMPONENT_INDEX_MOST_POPULAR_TV_SHOWS = 3
    private val COMPONENT_INDEX_MOST_POPULAR_PEOPLE = 4

    @Test
    fun fetchResourcesFromRepository() = runBlockingTest {

        mockSuccessfulCase()

        verify(repository, times(fetchInvocationCount)).getMostPopularMovies()
        verify(repository, times(fetchInvocationCount)).getTopRatedMovies()
        verify(repository, times(fetchInvocationCount)).getMostPopularTvShows()
        verify(repository, times(fetchInvocationCount)).getPopularPeoples()
    }

    @Test
    fun showSpinnerWhileLoading() = runBlockingTest {

        val viewModel = mockLoadingCase()

        assertEquals(
            loadingState.status,
            viewModel.homeComponents[COMPONENT_INDEX_MOST_POPULAR_MOVIES].data?.status
        )
        assertEquals(
            loadingState.status,
            viewModel.homeComponents[COMPONENT_INDEX_TOP_RATED_MOVIES].data?.status
        )
        assertEquals(
            loadingState.status,
            viewModel.homeComponents[COMPONENT_INDEX_MOST_POPULAR_TV_SHOWS].data?.status
        )
        assertEquals(
            loadingState.status,
            viewModel.homeComponents[COMPONENT_INDEX_MOST_POPULAR_PEOPLE].data?.status
        )
    }

    @Test
    fun getDataFromRepository() = runBlockingTest {

        val viewModel = mockSuccessfulCase()

        assertEquals(
            popularResult,
            viewModel.homeComponents[COMPONENT_INDEX_MOST_POPULAR_MOVIES].data?.data
        )
        assertEquals(
            popularResult,
            viewModel.homeComponents[COMPONENT_INDEX_TOP_RATED_MOVIES].data?.data
        )
        assertEquals(
            popularTvResult,
            viewModel.homeComponents[COMPONENT_INDEX_MOST_POPULAR_TV_SHOWS].data?.data
        )
        assertEquals(
            popularPeopleResult,
            viewModel.homeComponents[COMPONENT_INDEX_MOST_POPULAR_PEOPLE].data?.data
        )
    }

    @Test
    fun showErrorOnRecieveError() {

        val viewModel = mockErrorCase()

        assertEquals(
            errorState.status,
            viewModel.homeComponents[COMPONENT_INDEX_MOST_POPULAR_MOVIES].data?.status
        )
        assertEquals(
            errorState.status,
            viewModel.homeComponents[COMPONENT_INDEX_TOP_RATED_MOVIES].data?.status
        )
        assertEquals(
            errorState.status,
            viewModel.homeComponents[COMPONENT_INDEX_MOST_POPULAR_TV_SHOWS].data?.status
        )
        assertEquals(
            errorState.status,
            viewModel.homeComponents[COMPONENT_INDEX_MOST_POPULAR_PEOPLE].data?.status
        )
    }

    private fun mockErrorCase(): HomeViewModel {
        runBlockingTest {
            whenever(repository.getMostPopularMovies()).thenReturn(
                flow {
                    emit(errorState)
                }
            )
            whenever(repository.getTopRatedMovies()).thenReturn(
                flow {
                    emit(errorState)
                }
            )
            whenever(repository.getMostPopularTvShows()).thenReturn(
                flow {
                    emit(errorState)
                }
            )
            whenever(repository.getPopularPeoples()).thenReturn(
                flow {
                    emit(errorState)
                }
            )
        }
        return HomeViewModel(repository)
    }

    private fun mockSuccessfulCase(): HomeViewModel {
        runBlockingTest {
            whenever(repository.getMostPopularMovies()).thenReturn(
                flow {
                    emit(Resource.success(popularResult))
                }
            )
            whenever(repository.getTopRatedMovies()).thenReturn(
                flow {
                    emit(Resource.success(popularResult))
                }
            )
            whenever(repository.getMostPopularTvShows()).thenReturn(
                flow {
                    emit(Resource.success(popularTvResult))
                }
            )
            whenever(repository.getPopularPeoples()).thenReturn(
                flow {
                    emit(Resource.success(popularPeopleResult))
                }
            )
        }
        return HomeViewModel(repository)
    }

    private fun mockLoadingCase(): HomeViewModel {
        runBlockingTest {
            whenever(repository.getMostPopularMovies()).thenReturn(
                flow {
                    emit(loadingState)
                }
            )
            whenever(repository.getTopRatedMovies()).thenReturn(
                flow {
                    emit(loadingState)
                }
            )
            whenever(repository.getMostPopularTvShows()).thenReturn(
                flow {
                    emit(loadingState)
                }
            )
            whenever(repository.getPopularPeoples()).thenReturn(
                flow {
                    emit(loadingState)
                }
            )
        }
        return HomeViewModel(repository)
    }
}