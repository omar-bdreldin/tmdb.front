package com.omarbadreldin.base.recycler

class ListItemRecyclerAdapter(
    layoutResSupplier: LayoutResSupplier,
    viewHolderCreator: ViewHolderCreator<ListItem<*>>,
) : RecyclerAdapter<ListItem<*>, BindingViewHolder<*, ListItem<*>>>(
    layoutResSupplier = layoutResSupplier,
    viewHolderCreator = viewHolderCreator
)