package com.mclowicz.mcmovie.features.home.components

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import com.mclowicz.mcmovie.data.model.person.PopularPeopleResult
import com.mclowicz.mcmovie.data.model.person.PopularPerson
import com.mclowicz.mcmovie.util.Resource

class MostPopularPeopleComponent (
    override val data: Resource<PopularPeopleResult>
) : HomeComponent(data) {

    override var title: MutableLiveData<String?> = MutableLiveData(null)
    override val items: ObservableArrayList<PopularPerson> = ObservableArrayList()

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