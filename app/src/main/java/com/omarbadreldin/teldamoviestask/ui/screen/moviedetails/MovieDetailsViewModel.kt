package com.omarbadreldin.teldamoviestask.ui.screen.moviedetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.omarbadreldin.base.mvi.MviViewModel
import com.omarbadreldin.teldamoviestask.usecase.movie.details.MovieIdParams
import com.omarbadreldin.teldamoviestask.usecase.movie.details.MovieDetailsUseCase
import com.omarbadreldin.teldamoviestask.usecase.movie.similar.SimilarMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val movieDetailsUseCase: MovieDetailsUseCase,
    private val similarMoviesUseCase: SimilarMoviesUseCase,
    private val movieCreditsUseCase: MovieCreditsUseCase,
) : MviViewModel<MovieDetailsMVI.Intent, MovieDetailsMVI.State>(), MovieDetailsMVI.Model {

    private val args: MovieDetailsFragmentArgs by lazy {
        MovieDetailsFragmentArgs.fromSavedStateHandle(savedStateHandle)
    }

    init {
        loadMovieDetails()
    }

    private fun loadMovieDetails() {
        viewModelScope.launch {
            state(MovieDetailsMVI.State.Loading(true))
            val movieIdParams = MovieIdParams(args.movie.id ?: throw IllegalStateException())
            // get movie details (1st section)
            val movieDetails = movieDetailsUseCase.get(movieIdParams)
            state(MovieDetailsMVI.State.MovieDetailsLoaded(movieDetails))

            // get similar movies
            val similarMovies = similarMoviesUseCase.get(movieIdParams)
            state(MovieDetailsMVI.State.SimilarMoviesLoaded(similarMovies))


            state(MovieDetailsMVI.State.Loading(true))
        }
    }

    override fun onIntent(intent: MovieDetailsMVI.Intent) {
        when (intent) {
            MovieDetailsMVI.Intent.ToggleWishlist -> TODO()
        }
    }
}