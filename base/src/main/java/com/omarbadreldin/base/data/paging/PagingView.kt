package com.omarbadreldin.base.data.paging

import androidx.recyclerview.widget.RecyclerView
import com.omarbadreldin.base.mvi.MVI

interface PagingView<I : MVI.Intent, S : MVI.State, M : PagingModel<I, S>, PAGE : Page> :
    MVI.View<I, S, M>, OnScrolledToEndListener {

    val adapter: PagingAdapter

    val recyclerView: RecyclerView

    override fun render(state: S) {
        println("@omar: render(state = $state)")
        when (state) {
            is PageLoaded<*> -> onPageLoaded(state)
            is ListLoading -> onListLoading(state)
        }
    }

    fun onListLoading(state: ListLoading) {
        recyclerView.post {
            adapter.isLoading = state.isLoading
        }
    }

    fun onPageLoaded(state: PageLoaded<*>) {
        adapter.apply {
            appendAll(state.page.items)
        }
    }

    fun startPaging() = onScrolledToEnd()

    fun resetPaging() = viewModel.resetPaging()

    fun createAdapter(): PagingAdapter
}