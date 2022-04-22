package com.omarbadreldin.base.recycler

open class ListItemRecyclerAdapter(
    layoutResSupplier: LayoutResSupplier,
    viewHolderCreator: ViewHolderCreator<ListItem<*>>,
) : RecyclerAdapter<ListItem<*>, BindingViewHolder<*, ListItem<*>>>(
    layoutResSupplier = layoutResSupplier,
    viewHolderCreator = viewHolderCreator
) {

    override fun getItemViewType(position: Int): Int {
        return items[position].type
    }
}