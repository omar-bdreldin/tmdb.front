package com.omarbadreldin.teldamoviestask.ui.listing

import androidx.lifecycle.viewModelScope
import com.omarbadreldin.base.data.paging.LoadPage
import com.omarbadreldin.base.data.paging.ResetPaging
import com.omarbadreldin.base.mvi.MviViewModel
import com.omarbadreldin.teldamoviestask.data.datasource.movie.PopularMoviesDataSource
import com.omarbadreldin.teldamoviestask.data.datasource.movie.SearchMoviesDataSource
import com.omarbadreldin.teldamoviestask.data.model.paging.Page
import com.omarbadreldin.teldamoviestask.data.model.response.paging.hasReachedEnd
import com.omarbadreldin.teldamoviestask.data.model.response.paging.isFirstPage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListingViewModel @Inject constructor(
    private val popularMoviesDataSource: PopularMoviesDataSource,
    private val searchMoviesDataSource: SearchMoviesDataSource,
    private val popularMoviesItemsMapper: PopularMoviesItemsMapper,
) : MviViewModel<MovieListingMVI.Intent, MovieListingMVI.State>(), MovieListingMVI.Model {

    private var searchQuery: String? = null

    override fun onIntent(intent: MovieListingMVI.Intent) {
        when (intent) {
            is MovieListingMVI.Intent.Search -> doSearch(intent)
            else -> super.onIntent(intent)
        }
    }

    private fun doSearch(intent: MovieListingMVI.Intent.Search) {
        when {
            searchQuery.isNullOrEmpty() && intent.searchQuery.isNullOrEmpty() -> TODO("show validation error")
            searchQuery != intent.searchQuery -> {
                resetPaging(MovieListingMVI.Intent.ResetPaging)
                searchQuery = intent.searchQuery
                loadPage(MovieListingMVI.Intent.LoadPage)
            }
        }
    }

    override fun loadPage(intent: LoadPage) {
        if (intent !is MovieListingMVI.Intent.LoadPage) return
        viewModelScope.launch {
            state(MovieListingMVI.State.ListLoading(true))
            val query = searchQuery
            val data = if (query.isNullOrEmpty()) popularMoviesDataSource.runCatching { get() }
            else searchMoviesDataSource.runCatching { get(query) }
            state(MovieListingMVI.State.ListLoading(false))
            val pageState = data.getOrNull()?.run {
                MovieListingMVI.State.PageLoaded(
                    Page(
                        items = popularMoviesItemsMapper.invoke(this),
                        hasReachedEnd = hasReachedEnd(),
                        isFirstPage = isFirstPage(),
                    )
                )
            } ?: MovieListingMVI.State.ListError(
                data.exceptionOrNull() ?: throw IllegalStateException()
            )
            state(pageState)
        }
    }

    override fun resetPaging(intent: ResetPaging) {
        searchQuery = null
        state(MovieListingMVI.State.ClearPages)
        popularMoviesDataSource.reset()
        searchMoviesDataSource.reset()
    }
}