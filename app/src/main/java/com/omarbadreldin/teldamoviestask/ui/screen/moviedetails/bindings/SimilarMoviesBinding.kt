package com.omarbadreldin.teldamoviestask.ui.screen.moviedetails.bindings

import android.view.View.VISIBLE
import androidx.navigation.NavController
import com.omarbadreldin.base.recycler.ListItem
import com.omarbadreldin.base.recycler.ListItemRecyclerAdapter
import com.omarbadreldin.teldamoviestask.R
import com.omarbadreldin.teldamoviestask.data.model.movie.Movie
import com.omarbadreldin.teldamoviestask.databinding.FragmentMovieDetailsBinding
import com.omarbadreldin.teldamoviestask.ui.screen.moviedetails.MovieDetailsFragmentArgs
import com.omarbadreldin.teldamoviestask.ui.screen.moviedetails.viewholder.MovieViewHolder

fun FragmentMovieDetailsBinding.bindSimilarMovies(
    movies: List<Movie>,
    navController: NavController
) {
    textViewHeaderSimilarMovies.visibility = VISIBLE
    val adapter = ListItemRecyclerAdapter(
        layoutResSupplier = { R.layout.list_item_movie_simple },
        viewHolderCreator = { _, view ->
            MovieViewHolder(view) { item, _ ->
                navController.navigate(
                    R.id.action_movie_details_fragment_self,
                    MovieDetailsFragmentArgs(item.data).toBundle()
                )
            }
        }
    )
    recyclerViewSimilarMovies.adapter = adapter
    adapter.appendAll(movies.map { ListItem(data = it) })
}