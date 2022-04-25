package com.omarbadreldin.teldamoviestask.ui.screen.movielisting

import android.content.res.Resources
import androidx.lifecycle.viewModelScope
import com.omarbadreldin.base.data.paging.LoadPage
import com.omarbadreldin.base.data.paging.ResetPaging
import com.omarbadreldin.base.mvi.MviViewModel
import com.omarbadreldin.base.recycler.ListItem
import com.omarbadreldin.base.usecase.EmptyParams
import com.omarbadreldin.teldamoviestask.R
import com.omarbadreldin.teldamoviestask.data.model.paging.Page
import com.omarbadreldin.teldamoviestask.data.model.response.paging.hasReachedEnd
import com.omarbadreldin.teldamoviestask.data.model.response.paging.isFirstPage
import com.omarbadreldin.teldamoviestask.usecase.movie.popular.PopularMoviesUseCase
import com.omarbadreldin.teldamoviestask.usecase.movie.search.SearchMoviesParam
import com.omarbadreldin.teldamoviestask.usecase.movie.search.SearchMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MovieListingViewModel @Inject constructor(
    private val popularMoviesDataSource: PopularMoviesUseCase,
    private val searchMoviesDataSource: SearchMoviesUseCase,
    private val resources: Resources,
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
                onIntent(MovieListingMVI.Intent.ResetPaging)
                searchQuery = intent.searchQuery
                onIntent(MovieListingMVI.Intent.LoadPage)
            }
        }
    }

    override fun loadPage(intent: LoadPage) {
        if (intent !is MovieListingMVI.Intent.LoadPage) return
        viewModelScope.launch(Dispatchers.IO) {

            withContext(Dispatchers.Main) {
                state(MovieListingMVI.State.ListLoading(true))
            }

            val query = searchQuery
            val data =
                if (query.isNullOrEmpty()) popularMoviesDataSource.runCatching { get(EmptyParams) }
                else searchMoviesDataSource.runCatching { get(SearchMoviesParam(query)) }

            withContext(Dispatchers.Main) {
                state(MovieListingMVI.State.ListLoading(false))
            }

            val pageState = data.getOrNull()?.run {
                if (items.isNullOrEmpty()) MovieListingMVI.State.EmptyPage("$query")
                else MovieListingMVI.State.PageLoaded(
                    Page(
                        items = items.map {
                            ListItem(
                                type = ViewTypes.TYPE_POPULAR_MOVIE,
                                data = it
                            )
                        }.let {
                                if (query.isNullOrEmpty() && isFirstPage())
                                    listOf(
                                        ListItem(
                                            ViewTypes.TYPE_HEADER,
                                            resources.getString(R.string.label_popular_movies_header)
                                        )
                                    ) + it
                                else it
                            },
                        hasReachedEnd = hasReachedEnd(),
                        isFirstPage = isFirstPage(),
                        pagingKey = page
                    )
                )
            } ?: MovieListingMVI.State.ListError(
                data.exceptionOrNull() ?: throw IllegalStateException()
            )

            withContext(Dispatchers.Main) {
                state(pageState)
            }
        }
    }

    override fun resetPaging(intent: ResetPaging) {
        searchQuery = null
        state(MovieListingMVI.State.ClearPages)
        popularMoviesDataSource.reset()
        searchMoviesDataSource.reset()
    }
}