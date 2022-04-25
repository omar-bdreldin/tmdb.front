package com.omarbadreldin.base.data.paging

interface RemotePage<T, KEY> {

    val items: List<T>

    val pagingKey: KEY
}