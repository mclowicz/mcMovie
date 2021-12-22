package com.mclowicz.mcmovie.data.model.person

import com.google.gson.annotations.SerializedName

data class PopularPersonDetail(
    val id: Int,
    val name: String,
    val popularity: Double,
    val adult: Boolean,
    val birthday: String?,
    val biography: String,
    @SerializedName("place_of_birth")
    val placeOfBirth: String?,
    @SerializedName("profile_path")
    val profilePath: String?
)