package com.omarbadreldin.teldamoviestask.ui.screen.moviedetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.omarbadreldin.base.mvi.MviViewModel
import com.omarbadreldin.teldamoviestask.usecase.movie.details.MovieDetailsParams
import com.omarbadreldin.teldamoviestask.usecase.movie.details.MovieDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val movieDetailsUseCase: MovieDetailsUseCase
) : MviViewModel<MovieDetailsMVI.Intent, MovieDetailsMVI.State>(), MovieDetailsMVI.Model {

    private val args: MovieDetailsFragmentArgs by lazy {
        MovieDetailsFragmentArgs.fromSavedStateHandle(savedStateHandle)
    }

    init {
        loadMovieDetails()
    }

    private fun loadMovieDetails() {
        viewModelScope.launch {
            val movieDetails = movieDetailsUseCase.get(
                MovieDetailsParams(args.movie.id ?: throw IllegalStateException())
            )
            state(MovieDetailsMVI.State.MovieDetailsLoaded(movieDetails))
        }
    }

    override fun onIntent(intent: MovieDetailsMVI.Intent) {
        when (intent) {
            MovieDetailsMVI.Intent.ToggleWishlist -> TODO()
        }
    }
}