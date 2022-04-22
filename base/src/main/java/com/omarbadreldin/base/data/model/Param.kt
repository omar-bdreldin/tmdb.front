package com.omarbadreldin.base.data.model

interface Param<T> {

    val key: String

    val value: T?

    val valueString: String
        get() = value.toString()
}