package com.omarbadreldin.base.mvi

import android.os.Bundle
import android.view.View
import androidx.viewbinding.ViewBinding
import com.omarbadreldin.base.fragment.BaseFragment

abstract class MviFragment<BINDING : ViewBinding, I : MVI.Intent, S : MVI.State, VM : MviViewModel<I, S>>
    : BaseFragment<BINDING>(), MVI.View<I, S, VM> {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.state.observe(viewLifecycleOwner, ::render)
    }
}