package com.omarbadreldin.base.data.paging

import com.omarbadreldin.base.mvi.MVI

interface PagingView<I : MVI.Intent, S : MVI.State, M : PagingModel<I, S>, PAGE : Page>
    : MVI.View<I, S, M>, OnScrolledToEndListener {

    val adapter: PagingAdapter

    override fun render(state: S) {
        when (state) {
            is PageLoaded<*> -> adapter.apply {
                isLoading = false
                appendAll(state.page.list)
            }
        }
    }
}