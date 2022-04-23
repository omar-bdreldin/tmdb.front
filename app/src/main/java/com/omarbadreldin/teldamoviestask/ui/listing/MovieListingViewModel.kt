package com.omarbadreldin.teldamoviestask.ui.listing

import androidx.lifecycle.viewModelScope
import com.omarbadreldin.base.mvi.MviViewModel
import com.omarbadreldin.base.recycler.ListItem
import com.omarbadreldin.teldamoviestask.data.datasource.movie.GetPopularMoviesDataSource
import com.omarbadreldin.teldamoviestask.data.model.paging.Page
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListingViewModel @Inject constructor(
    private val getPopularMoviesDataSource: GetPopularMoviesDataSource
) : MviViewModel<MovieListingMVI.Intent, MovieListingMVI.State>(), MovieListingMVI.Model {

    override fun loadPage() {
        viewModelScope.launch {
            _state.value = MovieListingMVI.State.ListLoading(true)
            val data = getPopularMoviesDataSource.runCatching { get() }
            _state.value = MovieListingMVI.State.ListLoading(false)
            _state.value = MovieListingMVI.State.PageLoaded(
                Page(
                    items = data.getOrNull()?.items?.map {
                        ListItem(type = ViewTypes.TYPE_MOVIE, data = it)
                    } ?: emptyList()
                )
            )
        }
    }

    override fun resetPaging() {

    }
}