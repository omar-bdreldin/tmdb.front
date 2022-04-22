package com.omarbadreldin.base.data.source

fun interface RemoteDataSource<out DATA> {

    suspend fun get(): DATA
}