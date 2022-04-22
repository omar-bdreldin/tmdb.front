package com.omarbadreldin.base.data.paging

import com.omarbadreldin.base.data.model.IntParam
import com.omarbadreldin.base.data.source.ApiDataSource

interface PagingDataSource<ITEM, DATA : RemotePage<ITEM>> : ApiDataSource<DATA> {

    val hasApiReachedEndPredicate: (DATA) -> Boolean
        get() = {
            it.items.isEmpty()
        }

    var pagingKey: IntParam

    val perPageKey: IntParam

    override suspend fun get(): DATA {
        val data: DATA = getActual()
        val hasReachedEnd = hasApiReachedEndPredicate.invoke(data)
        pagingKey++
        return data
    }

    suspend fun getActual(): DATA

    fun reset()
}