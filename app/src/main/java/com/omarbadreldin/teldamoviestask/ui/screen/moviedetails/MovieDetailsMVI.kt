package com.omarbadreldin.teldamoviestask.ui.screen.moviedetails

import com.omarbadreldin.base.mvi.MVI
import com.omarbadreldin.base.mvi.common.LoadingState
import com.omarbadreldin.teldamoviestask.data.model.credits.Cast
import com.omarbadreldin.teldamoviestask.data.model.movie.Movie
import com.omarbadreldin.teldamoviestask.data.model.movie.details.MovieDetails

interface MovieDetailsMVI : MVI {

    interface View : MVI.View<Intent, State, Model>

    interface Model : MVI.Model<Intent, State>

    sealed interface Intent : MVI.Intent {
        object ToggleWishlist : Intent
    }

    sealed interface State : MVI.State {
        data class Loading(override val isLoading: Boolean) : State, LoadingState
        data class MovieDetailsLoaded(val movieDetails: MovieDetails) : State
        data class SimilarMoviesLoaded(val similarMovies: List<Movie>) : State
        data class CastLoaded(val castGroupedByDepartment: Map<String, List<Cast>>) : State
    }
}