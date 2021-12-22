package com.mclowicz.mcmovie.features.navigation

import com.mclowicz.mcmovie.features.detail.components.DetailComponentType

sealed class NavEvent() {
    class NavDetailEvent(
        val id: Int,
        val eventType: DetailComponentType
    ): NavEvent()
}