package com.omarbadreldin.base.recycler

import androidx.annotation.LayoutRes

fun interface LayoutResSupplier {

    @LayoutRes
    fun getLayoutResForViewType(viewType: Int): Int
}