package com.omarbadreldin.teldamoviestask.ui.screen.moviedetails

import androidx.fragment.app.viewModels
import com.omarbadreldin.base.mvi.MviFragment
import com.omarbadreldin.teldamoviestask.data.model.movie.details.MovieDetails
import com.omarbadreldin.teldamoviestask.databinding.FragmentMovieDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsFragment :
    MviFragment<FragmentMovieDetailsBinding, MovieDetailsMVI.Intent, MovieDetailsMVI.State, MovieDetailsViewModel>() {

    override val bindingInflater: () -> FragmentMovieDetailsBinding
        get() = {
            FragmentMovieDetailsBinding.inflate(layoutInflater)
        }

    override val viewModel: MovieDetailsViewModel by viewModels()

    override fun setupViews() {

    }

    override fun setup() {

    }

    override fun render(state: MovieDetailsMVI.State) {
        when (state) {
            is MovieDetailsMVI.State.MovieDetailsLoaded -> bindMovieDetails(state.movieDetails)
        }
    }

    private fun bindMovieDetails(movieDetails: MovieDetails) {

    }
}