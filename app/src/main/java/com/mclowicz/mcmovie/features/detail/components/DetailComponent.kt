package com.mclowicz.mcmovie.features.detail.components

import androidx.lifecycle.MutableLiveData
import com.mclowicz.mcmovie.data.repository.MovieRepository
import com.mclowicz.mcmovie.util.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

sealed class DetailComponent(
    open val repository: MovieRepository,
    open val id: Int
) {
    abstract val loadingState: Resource<Any>
    abstract val errorState: Resource<Any>
    abstract val data: MutableLiveData<*>

    abstract fun fetchData(id: Int)

    protected fun <T> emitRequest(
        flow: Flow<Resource<T>>,
        loadingState: Resource<T>,
        errorState: Resource<T>,
        data: MutableLiveData<Resource<T>>
    ) {
        CoroutineScope(Dispatchers.IO + SupervisorJob()).launch {
            flow
                .onStart { emit(loadingState) }
                .catch { emit(errorState.apply { message = it.localizedMessage }) }
                .onEach { data.postValue(it) }
                .collect {  }
        }
    }
}