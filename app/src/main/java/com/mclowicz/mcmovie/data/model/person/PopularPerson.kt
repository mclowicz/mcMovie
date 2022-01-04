package com.mclowicz.mcmovie.data.model.person

import com.google.gson.annotations.SerializedName
import com.mclowicz.mcmovie.data.model.movie.PopularMovie
import com.mclowicz.mcmovie.features.more.paging.MoreResponse

data class PopularPerson(
    val id: Int,
    val name: String,
    val popularity: Double,
    val adult: Boolean,
    @SerializedName("profile_path")
    val profilePath: String
)  : MoreResponse {

    override fun getItemViewType(): Int = MoreResponse.TYPE_POPULAR_PERSON

    override fun isSame(other: MoreResponse): Boolean {
        return if (other is PopularMovie) {
            id == other.id
        } else {
            false
        }
    }
}