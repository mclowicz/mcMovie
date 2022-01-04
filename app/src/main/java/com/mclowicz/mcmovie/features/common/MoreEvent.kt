package com.mclowicz.mcmovie.features.common

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MoreEvent(
    val title: String,
    val type: MoreType
) : Parcelable