package com.omarbadreldin.base.data.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.omarbadreldin.base.recycler.*
import com.omarbadreldin.base.recycler.common.EmptyViewHolder

private const val TYPE_LIST_LOADING = 23948;
private const val BEFORE_THRESHOLD_DEFAULT = 4

class PagingAdapter(
    layoutResSupplier: LayoutResSupplier,
    viewHolderCreator: ViewHolderCreator,
    private val beforeThreshold: Int = BEFORE_THRESHOLD_DEFAULT,
    private val onScrolledToEndListener: OnScrolledToEndListener,
    @LayoutRes private val loadingLayoutRes: Int,
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
                        data = true
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

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BindingViewHolder<*, ListItem<*>> {
        return if (viewType == TYPE_LIST_LOADING) LayoutInflater.from(parent.context).inflate(
            loadingLayoutRes,
            parent,
            false
        ).let { EmptyViewHolder(it) }
        else super.onCreateViewHolder(parent, viewType)
    }
}