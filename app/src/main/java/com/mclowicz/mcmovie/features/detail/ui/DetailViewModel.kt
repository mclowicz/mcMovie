package com.mclowicz.mcmovie.features.detail.ui

import androidx.databinding.ObservableArrayList
import com.mclowicz.mcmovie.data.repository.MovieRepository
import com.mclowicz.mcmovie.features.base.BaseViewModel
import com.mclowicz.mcmovie.features.detail.components.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: MovieRepository
) : BaseViewModel() {

    val detailComponent = ObservableArrayList<DetailComponent>()

    fun setComponent(id: Int, eventType: DetailComponentType) {
        DetailScreenComposer.compose(id, eventType, repository)
            .also {
                detailComponent.apply {
                    clear()
                    addAll(listOf(it))
                }
            }
    }
}