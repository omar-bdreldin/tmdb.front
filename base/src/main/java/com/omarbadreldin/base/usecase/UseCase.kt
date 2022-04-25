package com.omarbadreldin.base.usecase

interface UseCase<in P: Params, out RESULT> {

    suspend fun get(params: P): RESULT
}