package com.mclowicz.mcmovie.features.more

import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.mclowicz.mcmovie.R
import com.mclowicz.mcmovie.databinding.FragmentMoreBinding
import com.mclowicz.mcmovie.features.base.BaseFragment
import com.mclowicz.mcmovie.features.main.MainActivity
import com.mclowicz.mcmovie.features.more.paging.MoreAdapter
import com.mclowicz.mcmovie.features.navigation.NavEvent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MoreFragment : BaseFragment<FragmentMoreBinding, MoreViewModel>(
    R.layout.fragment_more
) {

    private val arguments: MoreFragmentArgs by navArgs()
    lateinit var adapter: MoreAdapter

    override fun setInitialVariables() {
        arguments.let {
            (requireActivity() as MainActivity).supportActionBar?.title = "${it.moreEvent.title}"
        }
        adapter = MoreAdapter(viewLifecycleOwner, viewModel)
    }

    override fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getPaging(arguments.moreEvent.type).collectLatest { response ->
                adapter.submitData(viewLifecycleOwner.lifecycle, response)
            }
        }
        with(viewModel) {
            getNavDetailEvent().observe(viewLifecycleOwner) { navEvent ->
                handleNavEvents(navEvent)
            }
        }
    }

    override fun bindUI() {
        binding.apply {
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = adapter
        }
    }

    private fun handleNavEvents(navEvent: NavEvent) {
        if (navEvent is NavEvent.NavDetailEvent) {
            MoreFragmentDirections.actionMoreFragmentToDestinationDetail(
                navEvent.id, navEvent.eventType
            ).also { findNavController().navigate(it) }
        }
    }
}