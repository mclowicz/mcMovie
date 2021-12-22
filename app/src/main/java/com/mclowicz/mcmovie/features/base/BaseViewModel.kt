package com.mclowicz.mcmovie.features.base

import androidx.lifecycle.ViewModel
import com.mclowicz.mcmovie.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart

abstract class BaseViewModel : ViewModel() {

    protected val loadingState = Resource.loading(null)
    protected val errorState = Resource.error(String(), null)

    fun <T> request(
        flow: Flow<Resource<T>>
    ): Flow<Resource<T>> {
        return flow
            .onStart { emit(loadingState) }
            .catch { emit(errorState.apply { message = it.localizedMessage }) }
    }
}