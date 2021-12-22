package com.mclowicz.mcmovie.util.kodebase

/**
 * Created by Stepan on 23.11.2016.
 * Please, visit www.github.com/kodebase-android
 * for further Kodebase library explanation
 */

interface RecyclerLayoutStrategy {
    fun getLayoutId(item: Any): Int
}