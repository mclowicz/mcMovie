package com.mclowicz.mcmovie.util.kodebase

import androidx.annotation.LayoutRes
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.ViewModel

/**
 * Created by Stepan on 23.11.2016.
 * Please, visit www.github.com/kodebase-android
 * for further Kodebase library explanation
 */

class SingleTypeRecyclerAdapter<T> : KodebaseRecyclerAdapter<T> {

    @LayoutRes
    private var layoutId: Int = 0

    constructor(items: ObservableArrayList<T>?, viewModel: ViewModel?, itemLaoyutId: Int) : super(items, viewModel) {
        this.layoutId = itemLaoyutId
    }

    constructor(items: ObservableArrayList<T>, itemLaoyutId: Int) : super(items) {
        this.layoutId = itemLaoyutId
    }

    override fun getLayoutId(itemType: Int): Int {
        return layoutId
    }
}