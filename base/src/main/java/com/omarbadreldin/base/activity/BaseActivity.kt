package com.omarbadreldin.base.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<BINDING: ViewBinding> : AppCompatActivity() {

    protected lateinit var binding: BINDING

    protected abstract val bindingInflater: () -> BINDING

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingInflater.invoke().also {
            binding = it
            setContentView(it.root)
        }
    }
}