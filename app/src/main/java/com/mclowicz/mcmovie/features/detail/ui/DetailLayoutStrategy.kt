package com.mclowicz.mcmovie.features.detail.ui

import com.mclowicz.mcmovie.R
import com.mclowicz.mcmovie.features.detail.components.MovieComponent
import com.mclowicz.mcmovie.features.detail.components.PersonComponent
import com.mclowicz.mcmovie.features.detail.components.TvComponent
import com.mclowicz.mcmovie.util.kodebase.RecyclerLayoutStrategy

object DetailLayoutStrategy : RecyclerLayoutStrategy {
    override fun getLayoutId(item: Any): Int {
        return when (item) {
            is MovieComponent -> R.layout.layout_detail_movie
            is TvComponent -> R.layout.layout_detail_tv
            is PersonComponent -> R.layout.layout_detail_person
            else -> throw Exception("Unknown RecyclerView Layout Strategy!")
        }
    }
}