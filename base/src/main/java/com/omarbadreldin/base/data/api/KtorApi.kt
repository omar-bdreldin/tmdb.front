package com.omarbadreldin.base.data.api

import com.omarbadreldin.base.data.model.Param
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.util.reflect.*

abstract class KtorApi<DATA> constructor(
    private val client: HttpClient,
    override val url: String,
    override val method: HttpMethod = HttpMethod.Get,
    private val typeInfo: TypeInfo,
) : Api<DATA> {

    override suspend fun call(
        url: String,
        method: HttpMethod,
        queryParams: Iterable<Param<*>>,
        bodyParams: Iterable<Param<*>>,
        pathParams: Iterable<Param<*>>,
        headers: Iterable<Param<*>>
    ): Result<DATA> {
        return runCatching {
            val urlActual: String = if (!pathParams.iterator().hasNext()) url
            else buildString {
                var modifiedUrl: String = url
                for (param in pathParams) {
                    modifiedUrl = modifiedUrl.replace(
                        oldValue = "{${param.key}}",
                        newValue = param.valueString
                    )
                }
                append(modifiedUrl)
            }

            val response: HttpResponse = client.request(urlString = urlActual) {
                this.method = method

                for (param in queryParams) {
                    parameter(param.key, param.valueString)
                }

                if (bodyParams.iterator().hasNext()) setBody(bodyParams)

                for (header in headers) {
                    header(header.key, header.valueString)
                }
            }
            response.body<DATA>(typeInfo)
        }
    }
}