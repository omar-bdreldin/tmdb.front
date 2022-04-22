package com.omarbadreldin.base.mvi.common

import com.omarbadreldin.base.mvi.MVI

abstract class LoadingState(
    val isLoading: Boolean = false
) : MVI.State

abstract class ErrorState(
    val error: Throwable
)