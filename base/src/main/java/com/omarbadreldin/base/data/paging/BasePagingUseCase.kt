package com.omarbadreldin.base.data.paging

import com.omarbadreldin.base.data.api.Api
import com.omarbadreldin.base.data.model.IntParam
import com.omarbadreldin.base.usecase.Params

abstract class BasePagingUseCase<P: Params, ITEM, DATA: RemotePage<ITEM, *>>(
    override val hasApiReachedEndPredicate: (DATA) -> Boolean,
    override var pagingKey: IntParam,
    override val perPageKey: IntParam,
    override val api: Api<DATA>,
) : PagingUseCase<P, ITEM, DATA>