package com.omarbadreldin.teldamoviestask.ui.screen.moviedetails

import com.omarbadreldin.base.mvi.MVI
import com.omarbadreldin.teldamoviestask.data.model.movie.details.MovieDetails

interface MovieDetailsMVI : MVI {

    interface View : MVI.View<Intent, State, Model>

    interface Model : MVI.Model<Intent, State>

    sealed interface Intent : MVI.Intent {
        object ToggleWishlist : Intent
    }

    sealed interface State : MVI.State {
        data class MovieDetailsLoaded(val movieDetails: MovieDetails) : State
    }
}