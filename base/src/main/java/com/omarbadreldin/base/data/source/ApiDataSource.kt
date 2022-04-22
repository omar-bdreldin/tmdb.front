package com.omarbadreldin.base.data.source

import com.omarbadreldin.base.data.api.Api

interface ApiDataSource<DATA> : RemoteDataSource<DATA> {

    val api: Api<DATA>
}