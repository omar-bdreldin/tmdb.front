package com.omarbadreldin.base.data.model

data class AnyParam<T>(
    override val key: String,
    override val value: T? = null
) : Param<T>


data class IntParam(
    override val key: String,
    override val value: Int? = null
) : Param<Int> {

    operator fun inc(): IntParam {
        return this.copy(
            value = value?.plus(1)
        )
    }

    operator fun dec(): IntParam {
        return this.copy(
            value = value?.minus(1)
        )
    }
}

infix fun <T : Any> String.paramOf(value: T?): Param<T> {
    return AnyParam(key = this, value = value)
}

infix fun String.intParamOf(value: Int?): IntParam {
    return IntParam(key = this, value = value)
}
