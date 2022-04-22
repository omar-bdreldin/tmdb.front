package com.omarbadreldin.base.data.api

import com.omarbadreldin.base.data.model.Param
import io.ktor.http.*

interface Api<out DATA> {

    val url: String

    val method: HttpMethod
        get() = HttpMethod.Get

    suspend fun call(
        url: String = this.url,
        method: HttpMethod = this.method,
        queryParams: Iterable<Param<*>> = emptyList(),
        bodyParams: Iterable<Param<*>> = emptyList(),
        pathParams: Iterable<Param<*>> = emptyList(),
        headers: Iterable<Param<*>> = emptyList(),
    ): Result<DATA>
}