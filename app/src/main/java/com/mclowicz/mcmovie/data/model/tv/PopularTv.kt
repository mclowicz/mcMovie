package com.mclowicz.mcmovie.data.model.tv

import com.google.gson.annotations.SerializedName
import com.mclowicz.mcmovie.data.model.movie.PopularMovie
import com.mclowicz.mcmovie.features.more.paging.MoreResponse

data class PopularTv(
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
    val voteCount: Int
) : MoreResponse {

    override fun getItemViewType(): Int = MoreResponse.TYPE_MOST_POPULAR_TV

    override fun isSame(other: MoreResponse): Boolean {
        return if (other is PopularMovie) {
            id == other.id
        } else {
            false
        }
    }
}