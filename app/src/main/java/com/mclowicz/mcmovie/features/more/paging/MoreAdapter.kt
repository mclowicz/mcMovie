package com.mclowicz.mcmovie.features.more.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mclowicz.mcmovie.data.model.movie.PopularMovie
import com.mclowicz.mcmovie.data.model.person.PopularPerson
import com.mclowicz.mcmovie.data.model.tv.PopularTv
import com.mclowicz.mcmovie.databinding.*
import com.mclowicz.mcmovie.features.more.MoreViewModel
import java.lang.Exception

class MoreAdapter(
    private val lifecycleOwner: LifecycleOwner,
    private val viewModel: MoreViewModel
) :
    PagingDataAdapter<MoreResponse, MoreAdapter.MoreViewHolder>(MoreResponse.MORE_COMPARATOR) {

    override fun onBindViewHolder(holder: MoreViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is PopularMovie -> MoreResponse.TYPE_MOST_POPULAR
            is PopularTv -> MoreResponse.TYPE_MOST_POPULAR_TV
            is PopularPerson -> MoreResponse.TYPE_POPULAR_PERSON
            else -> throw Exception("Unknown View Type!")
        }
    }

    abstract class MoreViewHolder(binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {
        abstract fun <T> bind(data: T)
    }

    inner class PopularMovieViewHolder(private val binding: ItemMoreMovieBinding) :
        MoreViewHolder(binding) {
        override fun <T> bind(data: T) {
            binding.apply {
                item = data as PopularMovie
                lifecycle = this@MoreAdapter.lifecycleOwner
                viewModel = this@MoreAdapter.viewModel
            }
        }
    }

    inner class PopularTvViewHolder(private val binding: ItemMoreTvBinding) :
        MoreViewHolder(binding) {
        override fun <T> bind(data: T) {
            binding.apply {
                item = data as PopularTv
                lifecycle = this@MoreAdapter.lifecycleOwner
                viewModel = this@MoreAdapter.viewModel
            }
        }
    }

    inner class PopularPeopleViewHolder(private val binding: ItemMorePersonBinding) :
        MoreViewHolder(binding) {
        override fun <T> bind(data: T) {
            binding.apply {
                item = data as PopularPerson
                lifecycle = this@MoreAdapter.lifecycleOwner
                viewModel = this@MoreAdapter.viewModel
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoreViewHolder {
        return when (viewType) {
            MoreResponse.TYPE_MOST_POPULAR -> {
                val binding = ItemMoreMovieBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                PopularMovieViewHolder(binding)
            }
            MoreResponse.TYPE_MOST_POPULAR_TV -> {
                val binding = ItemMoreTvBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                PopularTvViewHolder(binding)
            }
            MoreResponse.TYPE_POPULAR_PERSON -> {
                val binding = ItemMorePersonBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                PopularPeopleViewHolder(binding)
            }
            else -> throw Exception("Unknown ViewHolder!")
        }
    }
}

interface MoreResponse {
    fun getItemViewType(): Int
    fun isSame(other: MoreResponse): Boolean

    companion object {
        const val TYPE_MOST_POPULAR = 0
        const val TYPE_MOST_POPULAR_TV = 1
        const val TYPE_POPULAR_PERSON = 2
        val MORE_COMPARATOR = object : DiffUtil.ItemCallback<MoreResponse>() {
            override fun areItemsTheSame(oldItem: MoreResponse, newItem: MoreResponse) =
                oldItem.isSame(newItem)

            override fun areContentsTheSame(oldItem: MoreResponse, newItem: MoreResponse) =
                oldItem.equals(newItem)
        }
    }
}