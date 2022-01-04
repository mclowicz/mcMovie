package com.mclowicz.mcmovie.util.kodebase

import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Stepan on 23.11.2016.
 * Please, visit www.github.com/kodebase-android
 * for further Kodebase library explanation
 */

@BindingAdapter(
    value = ["viewModel", "items", "layoutId", "layoutStrategy", "lifecycle", "parentItem"],
    requireAll = false
)
fun <T>RecyclerView.bindItems(
    vm: ViewModel? = null,
    items: ObservableArrayList<T>? = null,
    layoutId: Int? = null,
    layoutStrategy: RecyclerLayoutStrategy? = null,
    lifecycleOwner: LifecycleOwner? = null,
    parentItem: Any? = null
) {
    if (this.adapter == null) {
        if (layoutStrategy == null) {
            if (layoutId != null) {
                this.adapter = SingleTypeRecyclerAdapter(items, vm, layoutId)
            }
        } else {
            this.adapter = MultiTypeRecyclerAdapter(items as ObservableArrayList<Any>, layoutStrategy, vm)
        }
    } else {
        (this.adapter as KodebaseRecyclerAdapter<T>).setItems(items)
    }
    (this.adapter as KodebaseRecyclerAdapter<*>).lifecycleOwner = lifecycleOwner
    (this.adapter as KodebaseRecyclerAdapter<*>).parentItem = parentItem
}