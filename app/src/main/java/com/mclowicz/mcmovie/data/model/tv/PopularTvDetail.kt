package com.mclowicz.mcmovie.data.model.tv

import com.google.gson.annotations.SerializedName

data class PopularTvDetail(
    val id: Int,
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    val name: String,
    val overview: String,
    val popularity: Double,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("vote_count")
    val voteCount: Int,
    @SerializedName("number_of_episodes")
    val numberOfEpisodes: Int,
    @SerializedName("number_of_seasons")
    val numberOfSeasons: Int,
    @SerializedName("first_air_date")
    val firstAirDate: String
)