package com.mclowicz.mcmovie.features.home.ui

import android.view.animation.AnimationUtils
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mclowicz.mcmovie.R
import com.mclowicz.mcmovie.databinding.FragmentHomeBinding
import com.mclowicz.mcmovie.features.base.BaseFragment
import com.mclowicz.mcmovie.features.navigation.NavEvent
import com.mclowicz.mcmovie.util.RecyclerViewOnMoveCallback
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(
    R.layout.fragment_home
) {

    companion object {
        const val INDEX_RECYCLER_VIEW_DIRECTION_DOWN = 1
    }

    override fun setInitialVariables() {}

    override fun initObservers() {
        with(viewModel) {
            getNavDetailEvent().observe(viewLifecycleOwner) { navEvent ->
                handleNavEvents(navEvent)
            }
        }
    }

    override fun bindUI() {
        val animation = AnimationUtils
            .loadLayoutAnimation(context, R.anim.layout_animation_up_to_down)
        binding.homeRecyclerView.layoutAnimation = animation
        ItemTouchHelper(RecyclerViewOnMoveCallback).apply {
            attachToRecyclerView(binding.homeRecyclerView)
        }

        binding.homeRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val position =
                    ((recyclerView.layoutManager) as LinearLayoutManager).findLastVisibleItemPosition()
                val holder = recyclerView.findViewHolderForAdapterPosition(position)
                if (dy > 0) {
                    // Scrolling up
                    holder?.itemView?.animation = AnimationUtils.loadAnimation(
                        holder?.itemView?.context,
                        R.anim.item_main_anim
                    )
                } else {
                    // Scrolling down

                }
            }
        })
    }

    private fun handleNavEvents(navEvent: NavEvent) {
        when (navEvent) {
            is NavEvent.NavMoreEvent -> {
                HomeFragmentDirections.actionDestinationHomeToMoreFragment(navEvent.moreEvent)
                    .also { findNavController().navigate(it) }
            }
            is NavEvent.NavDetailEvent -> {
                HomeFragmentDirections.actionDestinationHomeToDetailFragment(
                    navEvent.id, navEvent.eventType
                ).also { findNavController().navigate(it) }
            }
        }
    }
}