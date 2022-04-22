package com.omarbadreldin.base.mvi.common

import com.omarbadreldin.base.mvi.MVI

interface LoadingState : MVI.State {
    val isLoading: Boolean
}

interface ErrorState {
    val error: Throwable
}