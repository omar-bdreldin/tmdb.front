package com.omarbadreldin.base.data.paging

import com.omarbadreldin.base.mvi.MVI

interface PagingModel<I: MVI.Intent, S: MVI.State> : MVI.Model<I, S> {

    override fun onIntent(intent: I) {
        when (intent) {
            is LoadPage -> loadPage()
        }
    }

    fun loadPage()

    fun resetPaging()
}