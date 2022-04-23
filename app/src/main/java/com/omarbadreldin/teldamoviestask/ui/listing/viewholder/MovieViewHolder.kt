package com.omarbadreldin.teldamoviestask.ui.listing.viewholder

import android.view.View
import com.omarbadreldin.base.recycler.BindingViewHolder
import com.omarbadreldin.base.recycler.ListItem
import com.omarbadreldin.teldamoviestask.data.model.movie.Movie
import com.omarbadreldin.teldamoviestask.databinding.ListItemMovieBinding

class MovieViewHolder(itemView: View) :
    BindingViewHolder<ListItemMovieBinding, ListItem<Movie>>(itemView) {

    override val binder: (View) -> ListItemMovieBinding = {
        ListItemMovieBinding.bind(it)
    }

    override fun bind(item: ListItem<Movie>, position: Int) {

    }
}