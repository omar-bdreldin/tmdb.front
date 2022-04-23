package com.omarbadreldin.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<BINDING : ViewBinding> : Fragment() {

    protected lateinit var binding: BINDING

    protected abstract val bindingInflater: () -> BINDING

    protected val navController: NavController
        get() = findNavController()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return bindingInflater.invoke().let {
            binding = it
            it.root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        if (savedInstanceState == null) setup()
    }

    abstract fun setupViews()

    abstract fun setup()
}