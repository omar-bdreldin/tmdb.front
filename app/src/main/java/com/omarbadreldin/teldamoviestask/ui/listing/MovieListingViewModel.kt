package com.omarbadreldin.teldamoviestask.ui.listing

import androidx.lifecycle.viewModelScope
import com.omarbadreldin.base.data.paging.LoadPage
import com.omarbadreldin.base.mvi.MviViewModel
import com.omarbadreldin.base.recycler.ListItem
import com.omarbadreldin.teldamoviestask.data.datasource.movie.GetPopularMoviesDataSource
import com.omarbadreldin.teldamoviestask.data.model.paging.Page
import com.omarbadreldin.teldamoviestask.data.model.response.paging.hasReachedEnd
import com.omarbadreldin.teldamoviestask.data.model.response.paging.isFirstPage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListingViewModel @Inject constructor(
    private val getPopularMoviesDataSource: GetPopularMoviesDataSource,
    private val popularMoviesItemsMapper: PopularMoviesItemsMapper
) : MviViewModel<MovieListingMVI.Intent, MovieListingMVI.State>(), MovieListingMVI.Model {

    override fun loadPage(intent: LoadPage) {
        viewModelScope.launch {
            state(MovieListingMVI.State.ListLoading(true))
            val data = getPopularMoviesDataSource.runCatching { get() }
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

    override fun resetPaging() {
        getPopularMoviesDataSource.reset()
    }
}