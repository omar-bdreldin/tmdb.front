package com.omarbadreldin.base.recycler

fun interface ItemAction<T> {

    fun onAction(item: T, position: Int)
}