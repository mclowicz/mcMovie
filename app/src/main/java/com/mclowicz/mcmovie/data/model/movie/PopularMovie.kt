package com.mclowicz.mcmovie.data.model.movie

import com.google.gson.annotations.SerializedName
import com.mclowicz.mcmovie.features.more.paging.MoreResponse

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
) : MoreResponse {

    override fun getItemViewType(): Int = MoreResponse.TYPE_MOST_POPULAR

    override fun isSame(other: MoreResponse): Boolean {
        return if (other is PopularMovie) {
            id == other.id
        } else {
            false
        }
    }
}