package com.mclowicz.mcmovie.data.model.tv

import com.google.gson.annotations.SerializedName

data class PopularTvResult(
    val page: Int,
    val results: List<PopularTv>,
    @SerializedName("total_pages")
    val totalPages: Int
)