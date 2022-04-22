package com.omarbadreldin.base.recycler

import androidx.core.os.bundleOf
import com.omarbadreldin.base.BuildConfig

data class ListItem<out T>(
    val type: Int,
    val data: T,
) {

    init {
        /**
         * Insure that data can be serialized into a bundle
         */
        if (BuildConfig.DEBUG) bundleOf(
            ::data.name to data
        )
    }
}
