package com.mclowicz.mcmovie.features.detail.components

import com.mclowicz.mcmovie.data.repository.MovieRepository

class DetailScreenComposer {

    companion object {
        fun compose(
            id: Int,
            eventType: DetailComponentType,
            repository: MovieRepository
        ) : DetailComponent = when (eventType) {
            DetailComponentType.MOVIE -> MovieComponent(repository, id)
            DetailComponentType.TV -> TvComponent(repository, id)
            DetailComponentType.PERSON -> PersonComponent(repository, id)
        }
    }
}