package com.mclowicz.mcmovie.features.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.createViewModelLazy
import com.mclowicz.mcmovie.BR
import java.lang.reflect.ParameterizedType

abstract class BaseFragment<B : ViewDataBinding, VM : BaseViewModel>(
    @LayoutRes val layoutId: Int
) : Fragment() {

    private var _binding: B? = null
    protected val binding get() = _binding!!
    protected lateinit var viewModel: VM private set
    private val type = (javaClass.genericSuperclass as ParameterizedType)
    private val classVM = type.actualTypeArguments[1] as Class<VM>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        viewModel = createViewModelLazy(classVM.kotlin, { viewModelStore }).value
        binding.apply {
            binding.lifecycleOwner = viewLifecycleOwner
            binding.setVariable(BR.lifecycle, viewLifecycleOwner)
            binding.setVariable(BR.vm, viewModel)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setInitialVariables()
        initObservers()
        bindUI()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    abstract fun initObservers()
    abstract fun setInitialVariables()
    abstract fun bindUI()
}