package com.omarbadreldin.base.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class RecyclerAdapter<T, VH : BindingViewHolder<*, T>>(
    private val items: MutableList<T> = mutableListOf(),
    private val layoutResSupplier: LayoutResSupplier,
    private val viewHolderCreator: ViewHolderCreator<T>,
) : RecyclerView.Adapter<VH>(),
    RecyclerOps<T> {

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return viewHolderCreator.createViewHolderForViewType(
            viewType,
            LayoutInflater.from(parent.context).inflate(
                layoutResSupplier.getLayoutResForViewType(viewType),
                parent
            )
        ) as VH
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(item = items[position], position)
    }

    override fun getAll(): List<T> = items

    override fun appendAll(items: List<T>) {
        val insertIndex = itemCount
        this.items.addAll(items)
        notifyItemRangeInserted(insertIndex, items.size)
    }

    override fun prependAll(items: List<T>) {
        this.items.addAll(0, items)
        notifyItemRangeInserted(0, items.size)
    }

    override fun clearAll() {
        val itemCount = itemCount
        items.clear()
        notifyItemRangeRemoved(0, itemCount)
    }

    override fun update(position: Int, item: T) {
        items[position] = item
        notifyItemChanged(position)
    }

    override fun delete(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
    }
}