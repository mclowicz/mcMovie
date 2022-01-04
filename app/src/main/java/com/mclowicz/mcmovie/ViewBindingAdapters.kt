package com.mclowicz.mcmovie

import android.graphics.PorterDuff
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.mclowicz.mcmovie.features.home.components.HomeComponent

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

@BindingAdapter("component")
fun RecyclerView.setItemAnimation(component: HomeComponent) {
    addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            val position =
                ((recyclerView.layoutManager) as LinearLayoutManager).findLastVisibleItemPosition()
            val holder = recyclerView.findViewHolderForAdapterPosition(position)
            if (dx > 0) {
                holder?.itemView?.animation = AnimationUtils.loadAnimation(holder?.itemView?.context, R.anim.item_sub_anim)
            }
            if (!canScrollHorizontally(1)) {
//                    Toast.makeText(context, "Last", Toast.LENGTH_SHORT).show()
            }
        }

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == RecyclerView.SCROLL_STATE_IDLE) {
            }
        }
    })
}