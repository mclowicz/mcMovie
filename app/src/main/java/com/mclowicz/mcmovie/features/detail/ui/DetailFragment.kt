package com.mclowicz.mcmovie.features.detail.ui

import androidx.navigation.fragment.navArgs
import com.mclowicz.mcmovie.R
import com.mclowicz.mcmovie.databinding.FragmentDetailBinding
import com.mclowicz.mcmovie.features.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding, DetailViewModel>(
    R.layout.fragment_detail
) {

    private val arguments: DetailFragmentArgs by navArgs()

    override fun setInitialVariables() {
        arguments.let {
            viewModel.setComponent(it.id, it.detailComponentType)
        }
    }

    override fun initObservers() {}

    override fun bindUI() {}
}