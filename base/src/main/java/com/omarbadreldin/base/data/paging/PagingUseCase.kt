package com.omarbadreldin.base.data.paging

import com.omarbadreldin.base.data.model.IntParam
import com.omarbadreldin.base.usecase.ApiUseCase
import com.omarbadreldin.base.usecase.Params

interface PagingUseCase<P: Params, ITEM, DATA : RemotePage<ITEM, *>> : ApiUseCase<P, DATA> {

    val hasApiReachedEndPredicate: (DATA) -> Boolean
        get() = {
            it.items.isEmpty()
        }

    var pagingKey: IntParam

    val perPageKey: IntParam

    suspend fun getActual(params: P): DATA

    override suspend fun get(params: P): DATA {
        val data: DATA = getActual(params)
//        val hasReachedEnd = hasApiReachedEndPredicate.invoke(data)
        pagingKey++
        return data
    }

    fun reset()
}