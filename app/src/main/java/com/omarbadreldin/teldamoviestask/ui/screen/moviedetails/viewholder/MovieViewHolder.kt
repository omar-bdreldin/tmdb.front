package com.omarbadreldin.teldamoviestask.ui.screen.moviedetails.viewholder

import android.view.View
import coil.load
import coil.size.Scale
import coil.transform.RoundedCornersTransformation
import com.omarbadreldin.base.recycler.BindingViewHolder
import com.omarbadreldin.base.recycler.ItemAction
import com.omarbadreldin.base.recycler.ListItem
import com.omarbadreldin.teldamoviestask.R
import com.omarbadreldin.teldamoviestask.data.api.prependBaseMediaUrl
import com.omarbadreldin.teldamoviestask.data.model.movie.Movie
import com.omarbadreldin.teldamoviestask.databinding.ListItemMovieSimpleBinding
import com.omarbadreldin.teldamoviestask.util.ui.extension.clicksTo

class MovieViewHolder(
    itemView: View,
    private val onMovieClick: ItemAction<ListItem<Movie>>,
) : BindingViewHolder<ListItemMovieSimpleBinding, ListItem<Movie>>(itemView) {

    override val binder: (View) -> ListItemMovieSimpleBinding
        get() = ListItemMovieSimpleBinding::bind

    override fun bind(item: ListItem<Movie>, position: Int) {
        binding.apply {
            root clicksTo {
                onMovieClick.onAction(item, position)
            }
            imageViewPoster.load(item.data.posterPath?.prependBaseMediaUrl()) {
                crossfade(true)
            }
        }
    }
}