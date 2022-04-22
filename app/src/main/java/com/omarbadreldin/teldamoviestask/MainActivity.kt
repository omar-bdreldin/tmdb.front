package com.omarbadreldin.teldamoviestask

import com.omarbadreldin.base.activity.BaseActivity
import com.omarbadreldin.teldamoviestask.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val bindingInflater: () -> ActivityMainBinding
        get() = {
            ActivityMainBinding.inflate(layoutInflater)
        }
}