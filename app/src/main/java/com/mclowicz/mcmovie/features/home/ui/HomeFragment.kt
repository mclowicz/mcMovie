package com.mclowicz.mcmovie.features.home.ui

import androidx.navigation.fragment.findNavController
import com.mclowicz.mcmovie.R
import com.mclowicz.mcmovie.databinding.FragmentHomeBinding
import com.mclowicz.mcmovie.features.base.BaseFragment
import com.mclowicz.mcmovie.features.navigation.NavEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(
    R.layout.fragment_home
) {

    override fun initObservers() {
        with(viewModel) {
            getNavDetailEvent().observe(viewLifecycleOwner) { navEvent ->
                navigateToDetailFragment(navEvent)
            }
        }
    }

    private fun navigateToDetailFragment(navEvent: NavEvent) {
        val event = navEvent as NavEvent.NavDetailEvent
        HomeFragmentDirections.actionDestinationHomeToDetailFragment(event.id, event.eventType)
            .also { findNavController().navigate(it) }
    }
}