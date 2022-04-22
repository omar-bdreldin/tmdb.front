package com.omarbadreldin.base.data.paging

import com.omarbadreldin.base.recycler.*

private const val TYPE_LIST_LOADING = 23948;
private const val BEFORE_THRESHOLD_DEFAULT = 4

class PagingAdapter(
    layoutResSupplier: LayoutResSupplier,
    viewHolderCreator: ViewHolderCreator<ListItem<*>>,
    private val beforeThreshold: Int = BEFORE_THRESHOLD_DEFAULT,
    private val onScrolledToEndListener: OnScrolledToEndListener,
) : ListItemRecyclerAdapter(
    layoutResSupplier, viewHolderCreator
) {

    var isLoading: Boolean = false
        set(value) {
            field = value
            if (value) appendAll(
                listOf(
                    ListItem(
                        type = TYPE_LIST_LOADING,
                        data = Unit
                    )
                )
            )
            else delete(
                items.indexOfFirst { it.type == TYPE_LIST_LOADING }
            )
        }

    override fun onBindViewHolder(holder: BindingViewHolder<*, ListItem<*>>, position: Int) {
        super.onBindViewHolder(holder, position)
        if (!isLoading && position == itemCount - beforeThreshold)
            onScrolledToEndListener.onScrolledToEnd()
    }
}