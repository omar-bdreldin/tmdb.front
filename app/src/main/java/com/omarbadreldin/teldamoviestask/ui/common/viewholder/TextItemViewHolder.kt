package com.omarbadreldin.teldamoviestask.ui.common.viewholder

import android.view.View
import android.widget.TextView
import androidx.viewbinding.ViewBinding
import com.omarbadreldin.base.recycler.BindingViewHolder
import com.omarbadreldin.base.recycler.ListItem

open class TextItemViewHolder<BINDING: ViewBinding>(
    itemView: View,
    override val binder: (View) -> BINDING,
    private val textViewProvider: (binding: BINDING) -> TextView
) : BindingViewHolder<BINDING, ListItem<CharSequence>>(itemView) {

    private val textView: TextView by lazy {
        textViewProvider.invoke(binding)
    }


    override fun bind(item: ListItem<CharSequence>, position: Int) {
        textView.text = item.data
    }
}