package com.omarbadreldin.base.recycler

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BindingViewHolder<out BINDING: ViewBinding, in T>(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    protected val context: Context by lazy { binding.root.context }

    protected abstract val binder: (View) -> BINDING

    protected val binding: BINDING by lazy { binder.invoke(itemView) }

    abstract fun bind(item: T, position: Int)
}