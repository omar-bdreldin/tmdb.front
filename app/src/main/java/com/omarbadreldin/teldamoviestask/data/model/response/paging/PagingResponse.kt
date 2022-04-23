package com.omarbadreldin.teldamoviestask.data.model.response.paging


import com.omarbadreldin.base.data.paging.RemotePage
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PagingResponse<T>(
    @SerialName("page")
    val page: Int,
    @SerialName("results")
    val results: List<T> = emptyList(),
    @SerialName("total_pages")
    val totalPages: Int,
    @SerialName("total_results")
    val totalResults: Int,
) : RemotePage<T> {

    override val items: List<T> = results ?: emptyList()
}

fun <T> PagingResponse<T>.hasReachedEnd(): Boolean {
    return page == totalPages
}

fun <T> PagingResponse<T>.isFirstPage(): Boolean {
    return page == 1
}