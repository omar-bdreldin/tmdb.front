package com.omarbadreldin.teldamoviestask.ui.screen.moviedetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.omarbadreldin.base.mvi.MviViewModel
import com.omarbadreldin.teldamoviestask.data.model.credits.Departments
import com.omarbadreldin.teldamoviestask.usecase.movie.credits.MovieCreditsUseCase
import com.omarbadreldin.teldamoviestask.usecase.movie.details.MovieIdParams
import com.omarbadreldin.teldamoviestask.usecase.movie.details.MovieDetailsUseCase
import com.omarbadreldin.teldamoviestask.usecase.movie.similar.SimilarMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

private const val LIMIT_CAST = 5

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
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                state(MovieDetailsMVI.State.Loading(true))
            }
            val movieIdParams = MovieIdParams(args.movie.id ?: throw IllegalStateException())
            // get movie details (1st section)
            val movieDetails = movieDetailsUseCase.get(movieIdParams)
            withContext(Dispatchers.Main) {
                state(MovieDetailsMVI.State.MovieDetailsLoaded(movieDetails))
            }

            // get similar movies
            val similarMovies = similarMoviesUseCase.get(movieIdParams)
            withContext(Dispatchers.Main) {
                state(MovieDetailsMVI.State.SimilarMoviesLoaded(similarMovies))
            }

            // get cast of all similar movies
            val allCasts = similarMovies.asSequence().map {
                runBlocking {
                    movieCreditsUseCase.get(
                        MovieIdParams(it.id ?: throw IllegalStateException())
                    ).cast ?: emptyList()
                }
            }.flatten()
            val castGroupedByDepartment = allCasts.asSequence()
                .groupBy { it.knownForDepartment?.lowercase() }
            val actors = castGroupedByDepartment.getOrElse(Departments.ACTING) { emptyList() }
                .asSequence().sortedByDescending { it.popularity }.take(LIMIT_CAST).toList()
            val directors = castGroupedByDepartment.getOrElse(Departments.DIRECTING) { emptyList() }
                .asSequence().sortedByDescending { it.popularity }.take(LIMIT_CAST).toList()
            withContext(Dispatchers.Main) {
                state(MovieDetailsMVI.State.CastLoaded(actor = actors, directors = directors))
            }

            withContext(Dispatchers.Main) {
                state(MovieDetailsMVI.State.Loading(false))
            }
        }
    }

    override fun onIntent(intent: MovieDetailsMVI.Intent) {
        when (intent) {
            MovieDetailsMVI.Intent.ToggleWishlist -> TODO()
        }
    }
}