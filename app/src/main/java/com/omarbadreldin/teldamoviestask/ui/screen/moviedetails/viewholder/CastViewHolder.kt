package com.omarbadreldin.teldamoviestask.ui.screen.moviedetails.viewholder

import android.view.View
import coil.load
import com.omarbadreldin.base.recycler.BindingViewHolder
import com.omarbadreldin.base.recycler.ListItem
import com.omarbadreldin.teldamoviestask.data.api.prependBaseMediaUrl
import com.omarbadreldin.teldamoviestask.data.model.credits.Cast
import com.omarbadreldin.teldamoviestask.databinding.ListItemCastBinding

class CastViewHolder(itemView: View) :
    BindingViewHolder<ListItemCastBinding, ListItem<Cast>>(itemView) {

    override val binder: (View) -> ListItemCastBinding
        get() = ListItemCastBinding::bind

    override fun bind(item: ListItem<Cast>, position: Int) {
        binding.apply {
            imageViewPoster.load(item.data.profilePath?.prependBaseMediaUrl()) {
                crossfade(true)
            }
            textViewName.text = item.data.name.toString()
        }
    }
}