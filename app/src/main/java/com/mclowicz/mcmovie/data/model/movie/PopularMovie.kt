package com.mclowicz.mcmovie.data.model.movie


import com.google.gson.annotations.SerializedName

data class PopularMovie(
    val id: Int,
    val title: String?,
    val name: String?,
    @SerializedName("original_language")
    val originalLanguage: String,
    @SerializedName("vote_count")
    val voteCount: Int,
    val video: Boolean,
    @SerializedName("vote_average")
    val voteAverage: Double,
    val popularity: Double,
    @SerializedName("release_date")
    val releaseDate: String,
    val overview: String,
    @SerializedName("poster_path")
    val posterPath: String?
)