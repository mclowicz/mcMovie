package com.mclowicz.mcmovie

import android.graphics.PorterDuff
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable

@BindingAdapter("setVisibility")
fun View.setVisibility(isVisible: Boolean?) {
    isVisible?.let {
        val value = when (it) {
            true -> View.VISIBLE
            else -> View.GONE
        }
        visibility = value
    }
}

@BindingAdapter("imageUrl")
fun ImageView.setImage(imageUrl: String?) {
    imageUrl?.let {
        val circularProgressDrawable = CircularProgressDrawable(context)
            .apply {
                strokeWidth = 5f
                centerRadius = 30f
                setColorFilter(
                    ContextCompat.getColor(
                        context,
                        R.color.material_on_background_emphasis_high_type
                    ), PorterDuff.Mode.SRC_IN
                )
                start()
            }
        GlideApp.with(context)
            .load("https://image.tmdb.org/t/p/w500" + imageUrl)
            .placeholder(circularProgressDrawable)
            .error(R.drawable.ic_launcher_background)
            .into(this)
    }
}