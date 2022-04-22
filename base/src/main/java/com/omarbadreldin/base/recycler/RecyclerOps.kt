package com.omarbadreldin.base.recycler

interface RecyclerOps<T> {

    fun getAll(): List<T>

    fun appendAll(items: List<T>)

    fun prependAll(items: List<T>)

    fun clearAll()

    fun update(position: Int, item: T = getAll()[position])

    fun delete(position: Int)
}