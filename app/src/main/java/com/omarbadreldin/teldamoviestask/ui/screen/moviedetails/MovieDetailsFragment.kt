package com.omarbadreldin.teldamoviestask.ui.screen.moviedetails

import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.fragment.app.viewModels
import com.omarbadreldin.base.mvi.MviFragment
import com.omarbadreldin.teldamoviestask.databinding.FragmentMovieDetailsBinding
import com.omarbadreldin.teldamoviestask.ui.screen.moviedetails.bindings.bindMovieDetails
import com.omarbadreldin.teldamoviestask.ui.screen.moviedetails.bindings.bindSimilarMovies
import com.omarbadreldin.teldamoviestask.ui.screen.moviedetails.bindings.bindSimilarMoviesCast
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
            is MovieDetailsMVI.State.MovieDetailsLoaded ->
                binding.bindMovieDetails(state.movieDetails)
            is MovieDetailsMVI.State.CastLoaded ->
                binding.bindSimilarMoviesCast(state)
            is MovieDetailsMVI.State.SimilarMoviesLoaded ->
                binding.bindSimilarMovies(state.similarMovies, navController)
        }
    }
}