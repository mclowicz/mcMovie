package com.mclowicz.mcmovie.data.model.person

import com.google.gson.annotations.SerializedName

data class PopularPerson(
    val id: Int,
    val name: String,
    val popularity: Double,
    val adult: Boolean,
    @SerializedName("profile_path")
    val profilePath: String
)