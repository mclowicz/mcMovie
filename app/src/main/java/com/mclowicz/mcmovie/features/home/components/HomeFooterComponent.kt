package com.mclowicz.mcmovie.features.home.components

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import com.mclowicz.mcmovie.features.common.MoreType
import com.mclowicz.mcmovie.util.Resource

class HomeFooterComponent(
    override val data: Resource<Any>? = null
) : HomeComponent(data) {

    override var title: MutableLiveData<String?> = MutableLiveData(null)
    override val items: ObservableArrayList<Any> = ObservableArrayList()
    override var moreType: MoreType = MoreType.UNDEFINED

    override fun setTitle(title: String?) {
        this.title.value = title
    }

    override fun addMoreData() {

    }
}