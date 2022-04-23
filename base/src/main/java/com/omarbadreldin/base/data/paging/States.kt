package com.omarbadreldin.base.data.paging

import com.omarbadreldin.base.mvi.MVI
import com.omarbadreldin.base.mvi.common.ErrorState
import com.omarbadreldin.base.mvi.common.LoadingState

interface ListLoading : LoadingState

interface PageLoaded<PAGE: Page> : MVI.State {
    val page: PAGE
}

interface EmptyPageError : ErrorState

interface ClearList : MVI.State