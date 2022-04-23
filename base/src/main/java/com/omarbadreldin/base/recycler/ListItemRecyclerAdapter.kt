package com.omarbadreldin.base.recycler

open class ListItemRecyclerAdapter(
    layoutResSupplier: LayoutResSupplier,
    viewHolderCreator: ViewHolderCreator,
) : RecyclerAdapter<ListItem<*>, BindingViewHolder<*, ListItem<*>>>(
    layoutResSupplier = layoutResSupplier,
    viewHolderCreator = viewHolderCreator
) {

    override fun getItemViewType(position: Int): Int {
        return items[position].type
    }
}