package com.omarbadreldin.teldamoviestask.ui.screen.moviedetails.bindings

import android.view.View.VISIBLE
import com.omarbadreldin.base.recycler.ListItem
import com.omarbadreldin.base.recycler.ListItemRecyclerAdapter
import com.omarbadreldin.teldamoviestask.R
import com.omarbadreldin.teldamoviestask.databinding.FragmentMovieDetailsBinding
import com.omarbadreldin.teldamoviestask.ui.screen.moviedetails.MovieDetailsMVI
import com.omarbadreldin.teldamoviestask.ui.screen.moviedetails.viewholder.CastViewHolder

fun FragmentMovieDetailsBinding.bindSimilarMoviesCast(state: MovieDetailsMVI.State.CastLoaded) {
    textViewHeaderCast.visibility = VISIBLE
    val castAndRecyclerViewPairs = listOf(
        state.actor to recyclerViewCastActors,
        state.directors to recyclerViewCastDirectors
    )
    for ((cast, recyclerView) in castAndRecyclerViewPairs) {
        val adapter = ListItemRecyclerAdapter(
            layoutResSupplier = {
                R.layout.list_item_cast
            },
            viewHolderCreator = { _, view ->
                CastViewHolder(view)
            }
        )
        recyclerView.adapter = adapter
        adapter.appendAll(cast.map { ListItem(data = it) })
    }
}