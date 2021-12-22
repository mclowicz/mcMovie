package com.mclowicz.mcmovie.features.home.ui

import com.mclowicz.mcmovie.R
import com.mclowicz.mcmovie.data.model.movie.PopularMovie
import com.mclowicz.mcmovie.data.model.person.PopularPerson
import com.mclowicz.mcmovie.data.model.tv.PopularTv
import com.mclowicz.mcmovie.features.home.components.*
import com.mclowicz.mcmovie.util.kodebase.RecyclerLayoutStrategy

object HomeLayoutStrategy : RecyclerLayoutStrategy {
    override fun getLayoutId(item: Any): Int {
        return when (item) {
            is MostPopularMoviesComponent -> R.layout.layout_component_most_popular
            is MostPopularPeopleComponent -> R.layout.layout_component_most_popular
            is MostPopularTvComponent -> R.layout.layout_component_most_popular
            is PopularPerson -> R.layout.item_home_popular_person
            is PopularMovie -> R.layout.item_home_popular
            is PopularTv -> R.layout.item_home_popular_tv
            is HomeHeaderComponent -> R.layout.layout_home_header
            is HomeFooterComponent -> R.layout.layout_home_footer
            else -> throw Exception("Unknown RecyclerView Layout Strategy!")
        }
    }
}