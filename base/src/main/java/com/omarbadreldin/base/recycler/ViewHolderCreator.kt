package com.omarbadreldin.base.recycler

import android.view.View

fun interface ViewHolderCreator<T> {

    fun createViewHolderForViewType(viewType: Int, view: View): BindingViewHolder<*, T>
}