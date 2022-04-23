package com.omarbadreldin.teldamoviestask.ui.listing

import com.omarbadreldin.base.data.paging.*
import com.omarbadreldin.base.mvi.MVI
import com.omarbadreldin.base.mvi.common.ErrorState

interface MovieListingMVI : MVI {

    interface View : PagingView<Intent, State, MovieListingViewModel, Page>

    interface Model : PagingModel<Intent, State>

    sealed interface Intent : MVI.Intent {
        object LoadPage : Intent, com.omarbadreldin.base.data.paging.LoadPage

        data class Search(
            val searchQuery: String?
        ) : Intent

        object ResetPaging : Intent, com.omarbadreldin.base.data.paging.ResetPaging
    }

    sealed interface State : MVI.State {

        data class ListLoading(
            override val isLoading: Boolean
        ) : State, com.omarbadreldin.base.data.paging.ListLoading

        data class PageLoaded(
            override val page: Page
        ) : State, com.omarbadreldin.base.data.paging.PageLoaded<Page>

        data class ListError(override val error: Throwable) : State, ErrorState

        object ClearPages : State, ClearList

    }
}