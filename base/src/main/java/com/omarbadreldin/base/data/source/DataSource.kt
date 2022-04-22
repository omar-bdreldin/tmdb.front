package com.omarbadreldin.base.data.source

fun interface DataSource<out DATA> {

    fun get(): DATA
}