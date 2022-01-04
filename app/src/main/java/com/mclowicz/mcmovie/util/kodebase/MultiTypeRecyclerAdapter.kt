package com.mclowicz.mcmovie.util.kodebase

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.ViewModel

/**
 * Created by Stepan on 23.11.2016.
 * Please, visit www.github.com/kodebase-android
 * for further Kodebase library explanation
 */

class MultiTypeRecyclerAdapter(items: ObservableArrayList<Any>, var strategy: RecyclerLayoutStrategy, vm: ViewModel?) :
    KodebaseRecyclerAdapter<Any>(items, vm) {

    override fun getLayoutId(itemType: Int): Int {
        return itemType
    }

    override fun getItemViewType(position: Int): Int {
        return strategy.getLayoutId(getItem(position))
    }
}