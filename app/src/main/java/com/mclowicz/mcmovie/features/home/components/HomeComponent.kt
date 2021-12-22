package com.mclowicz.mcmovie.features.home.components

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import com.mclowicz.mcmovie.util.Resource

sealed class HomeComponent(
    open val data: Resource<Any>?
) {

    abstract var title: MutableLiveData<String?>
    abstract val items: ObservableArrayList<*>
    abstract fun setTitle(title: String?)
}