package com.mclowicz.mcmovie

import com.jakewharton.espresso.OkHttp3IdlingResource
import okhttp3.OkHttpClient

val client = OkHttpClient()
val idlingResource = OkHttp3IdlingResource.create("okhttp", client)