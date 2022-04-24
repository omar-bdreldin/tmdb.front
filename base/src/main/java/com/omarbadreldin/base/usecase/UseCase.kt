package com.omarbadreldin.base.usecase

interface UseCase<P: Params, RESULT> {

    suspend fun get(params: P): RESULT
}