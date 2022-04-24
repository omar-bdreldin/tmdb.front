package com.omarbadreldin.teldamoviestask.ui.screen.movielisting.viewholder

import android.view.View
import coil.load
import com.omarbadreldin.base.recycler.BindingViewHolder
import com.omarbadreldin.base.recycler.ItemAction
import com.omarbadreldin.base.recycler.ListItem
import com.omarbadreldin.teldamoviestask.data.api.Urls
import com.omarbadreldin.teldamoviestask.data.api.prependBaseMediaUrl
import com.omarbadreldin.teldamoviestask.data.model.movie.Movie
import com.omarbadreldin.teldamoviestask.databinding.ListItemMovieBinding
import com.omarbadreldin.teldamoviestask.util.ui.extension.clicksTo

class MovieViewHolder(
    itemView: View,
    private val onMovieClick: ItemAction<ListItem<Movie>>
) : BindingViewHolder<ListItemMovieBinding, ListItem<Movie>>(itemView) {

    override val binder: (View) -> ListItemMovieBinding = {
        ListItemMovieBinding.bind(it)
    }

    override fun bind(item: ListItem<Movie>, position: Int) {
        item.data.run {
            binding.apply {
                textViewTitle.text = "$title"
                textViewOverview.text = "$overview"
                imageViewMoviesPoster.load(
                    posterPath?.prependBaseMediaUrl()
                ) {
                    crossfade(true)
                }
                root clicksTo {
                    onMovieClick.onAction(item, position)
                }
            }
        }
    }
}