package com.omarbadreldin.teldamoviestask.ui.screen.moviedetails

import android.view.View.GONE
import android.view.View.VISIBLE
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
        println(state)
        when (state) {
            is MovieDetailsMVI.State.Loading -> {
                binding.loading.visibility = if (state.isLoading) VISIBLE else GONE
            }
            is MovieDetailsMVI.State.MovieDetailsLoaded -> bindMovieDetails(state.movieDetails)
            is MovieDetailsMVI.State.CastLoaded -> bindCast(state)
            is MovieDetailsMVI.State.SimilarMoviesLoaded -> bindSimilarMovies(state)
        }
    }

    private fun bindCast(state: MovieDetailsMVI.State.CastLoaded) {

    }

    private fun bindSimilarMovies(state: MovieDetailsMVI.State.SimilarMoviesLoaded) {

    }

    private fun bindMovieDetails(movieDetails: MovieDetails) {

    }
}