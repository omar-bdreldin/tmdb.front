package com.omarbadreldin.base.activity

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.viewbinding.ViewBinding
import com.omarbadreldin.base.constants.ErrorMessages.Dev.MESSAGE_NAV_HOST_FRAGMENT_NOT_FOUND

abstract class NavHostActivity<BINDING : ViewBinding> : BaseActivity<BINDING>() {

    private lateinit var navHostFragment: NavHostFragment

    protected abstract val navHostFragmentId: Int
        @IdRes get

    protected val navController: NavController
        get() = navHostFragment.navController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navHostFragment = supportFragmentManager.findFragmentById(
            navHostFragmentId
        ) as? NavHostFragment ?: throw IllegalStateException(MESSAGE_NAV_HOST_FRAGMENT_NOT_FOUND)
    }
}