package com.omarbadreldin.teldamoviestask.ui.screen.main

import com.omarbadreldin.base.activity.NavHostActivity
import com.omarbadreldin.teldamoviestask.R
import com.omarbadreldin.teldamoviestask.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : NavHostActivity<ActivityMainBinding>() {

    override val bindingInflater: () -> ActivityMainBinding = {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override val navHostFragmentId: Int = R.id.nav_host_fragment


}