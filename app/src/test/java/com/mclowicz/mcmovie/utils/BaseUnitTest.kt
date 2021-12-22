package com.mclowicz.mcmovie.utils

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mclowicz.mcmovie.data.repository.MovieRepository
import com.mclowicz.mcmovie.util.Resource
import com.nhaarman.mockitokotlin2.mock
import org.junit.Rule

abstract class BaseUnitTest {

    @get:Rule
    var coroutinesTestRule = MainCoroutineScopeRule()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    val repository: MovieRepository = mock()
    val networkError = Resource.error("Something went wrong!", null)
    val id: Int = 1
}