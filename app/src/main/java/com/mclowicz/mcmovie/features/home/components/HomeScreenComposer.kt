package com.mclowicz.mcmovie.features.home.components

import com.mclowicz.mcmovie.data.model.person.PopularPeopleResult
import com.mclowicz.mcmovie.data.model.movie.PopularResult
import com.mclowicz.mcmovie.data.model.tv.PopularTvResult
import com.mclowicz.mcmovie.util.Resource

class HomeScreenComposer {

    companion object {

        fun compose(
            moviesResource: Resource<PopularResult>,
            topRatedResource: Resource<PopularResult>,
            tvResource: Resource<PopularTvResult>,
            peoplesResource: Resource<PopularPeopleResult>
        ) = listOf(
            HomeHeaderComponent()
                .apply { setTitle(TITLE_HEADER) },
            MostPopularMoviesComponent(moviesResource)
                .apply { setTitle(TITLE_MOST_POPULAR_MOVIES) },
            MostPopularMoviesComponent(topRatedResource)
                .apply { setTitle(TITLE_TOP_RATED_MOVIES) },
            MostPopularTvComponent(tvResource)
                .apply { setTitle(TITLE_MOST_POPULAR_TV_SHOWS) },
            MostPopularPeopleComponent(peoplesResource)
                .apply { setTitle(TITLE_POPULAR_PEOPLES) },
            HomeFooterComponent()
        )

        const val TITLE_HEADER = "Happy Watching"
        const val TITLE_MOST_POPULAR_MOVIES = "Most Popular Movies"
        const val TITLE_TOP_RATED_MOVIES = "Top Rated Movies"
        const val TITLE_MOST_POPULAR_TV_SHOWS = "Most Popular TV Shows"
        const val TITLE_POPULAR_PEOPLES = "Popular Peoples"
    }
}