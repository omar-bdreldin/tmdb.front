package com.omarbadreldin.teldamoviestask.ui.listing

import android.content.res.Resources
import com.omarbadreldin.base.recycler.ListItem
import com.omarbadreldin.teldamoviestask.R
import com.omarbadreldin.teldamoviestask.data.model.movie.Movie
import com.omarbadreldin.teldamoviestask.data.model.response.paging.PagingResponse
import javax.inject.Inject

class PopularMoviesItemsMapper @Inject constructor(
    private val resources: Resources
) : (PagingResponse<Movie>) -> List<ListItem<*>> {

    override fun invoke(input: PagingResponse<Movie>): List<ListItem<*>> = input.run {
        val listItems = mutableListOf<ListItem<*>>()
        if (page == 1 && items.isNotEmpty()) listItems.add(
            ListItem(ViewTypes.TYPE_HEADER, resources.getString(R.string.label_popular_movies_header))
        )
        items.forEach {
            listItems.add(ListItem(type = ViewTypes.TYPE_POPULAR_MOVIE, data = it))
        }
        listItems
    }
}