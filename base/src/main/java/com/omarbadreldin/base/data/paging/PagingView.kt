package com.omarbadreldin.base.data.paging

import com.omarbadreldin.base.mvi.MVI

interface PagingView<I : MVI.Intent, S : MVI.State, M : PagingModel<I, S>, PAGE : Page>
    : MVI.View<I, S, M>, OnScrolledToEndListener {

    val adapter: PagingAdapter

    override fun render(state: S) {
        when (state) {
            is PageLoaded<*> -> onPageLoaded(state)
            is ListLoading -> onListLoading(state)
        }
    }

    fun onListLoading(state: ListLoading) {
        adapter.isLoading = state.isLoading
    }

    fun onPageLoaded(state: PageLoaded<*>) {
        adapter.apply {
            appendAll(state.page.items)
        }
    }
}