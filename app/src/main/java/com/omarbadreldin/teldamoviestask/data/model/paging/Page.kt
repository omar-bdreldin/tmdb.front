package com.omarbadreldin.teldamoviestask.data.model.paging

import com.omarbadreldin.base.data.paging.Page
import com.omarbadreldin.base.recycler.ListItem

data class Page(
    override val isFirstPage: Boolean = false,
    override val hasReachedEnd: Boolean = false,
    override val items: List<ListItem<*>> = emptyList(),
) : Page
