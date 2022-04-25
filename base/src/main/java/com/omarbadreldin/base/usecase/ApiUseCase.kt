package com.omarbadreldin.base.usecase

import com.omarbadreldin.base.data.api.Api

interface ApiUseCase<P: Params, out DATA> : UseCase<P, DATA> {

    val api: Api<DATA>
}