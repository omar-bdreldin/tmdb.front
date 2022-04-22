package com.omarbadreldin.base.data.paging

import com.omarbadreldin.base.data.api.Api
import com.omarbadreldin.base.data.model.IntParam

abstract class BasePagingDataSource<ITEM, DATA: RemotePage<ITEM>>(
    override val hasApiReachedEndPredicate: (DATA) -> Boolean,
    override var pagingKey: IntParam,
    override val perPageKey: IntParam,
    override val api: Api<DATA>,
) : PagingDataSource<ITEM, DATA>