package com.mclowicz.mcmovie.data.model.movie

import com.google.gson.annotations.SerializedName

data class PopularResult(
    val page: Int,
    val results: List<PopularMovie>,
    @SerializedName("total_pages")
    val totalPages: Int
)