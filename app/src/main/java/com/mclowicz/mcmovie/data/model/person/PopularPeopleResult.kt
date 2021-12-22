package com.mclowicz.mcmovie.data.model.person

import com.google.gson.annotations.SerializedName

data class PopularPeopleResult(
    val page: Int,
    val results: List<PopularPerson>,
    @SerializedName("total_pages")
    val totalPages: Int
)