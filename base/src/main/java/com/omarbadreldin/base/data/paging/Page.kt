package com.omarbadreldin.base.data.paging

import com.omarbadreldin.base.recycler.ListItem

interface Page : RemotePage<ListItem<*>, Int> {
    val isFirstPage: Boolean
    val hasReachedEnd: Boolean
}