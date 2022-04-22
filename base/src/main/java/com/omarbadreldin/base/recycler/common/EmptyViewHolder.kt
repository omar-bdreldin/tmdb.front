package com.omarbadreldin.base.recycler.common

import android.view.View
import androidx.viewbinding.ViewBinding
import com.omarbadreldin.base.recycler.BindingViewHolder

class EmptyViewHolder(itemView: View) : BindingViewHolder<ViewBinding, Any>(itemView) {

    override fun bind(item: Any, position: Int) {

    }

    override val binder: (View) -> ViewBinding
        get() = {
            ViewBinding { it }
        }
}