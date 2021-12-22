package com.mclowicz.mcmovie.features.home.components

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import com.mclowicz.mcmovie.data.model.tv.PopularTv
import com.mclowicz.mcmovie.data.model.tv.PopularTvResult
import com.mclowicz.mcmovie.util.Resource

class MostPopularTvComponent(
    override val data: Resource<PopularTvResult>
) : HomeComponent(data) {

    override var title: MutableLiveData<String?> = MutableLiveData(null)
    override val items: ObservableArrayList<PopularTv> = ObservableArrayList()

    init {
        refreshData()
    }

    private fun refreshData() {
        data.data?.results?.let {
            items.apply {
                clear()
                addAll(it)
            }
        }
    }

    override fun setTitle(title: String?) {
        this.title.value = title
    }
}