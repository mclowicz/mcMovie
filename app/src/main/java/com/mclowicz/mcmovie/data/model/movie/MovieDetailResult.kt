package com.mclowicz.mcmovie.data.model.movie

import com.google.gson.annotations.SerializedName

data class MovieDetailResult(
    val id: Int,
    @SerializedName("original_title")
    val title: String,
    val overview: String?,
    val popularity: Double,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    @SerializedName("release_date")
    val releaseDate: String,
    val revenue: Int,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("vote_count")
    val voteCount: Int,
    val budget: Int
)