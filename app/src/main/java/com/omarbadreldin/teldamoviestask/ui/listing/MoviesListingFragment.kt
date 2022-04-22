package com.omarbadreldin.teldamoviestask.ui.listing

import android.os.Bundle
import android.view.View
import com.omarbadreldin.base.data.paging.PagingAdapter
import com.omarbadreldin.base.fragment.BaseFragment
import com.omarbadreldin.base.recycler.BindingViewHolder
import com.omarbadreldin.base.recycler.LayoutResSupplier
import com.omarbadreldin.base.recycler.ListItem
import com.omarbadreldin.base.recycler.ViewHolderCreator
import com.omarbadreldin.teldamoviestask.R
import com.omarbadreldin.teldamoviestask.databinding.FragmentMoviesListingBinding

class MoviesListingFragment
    : BaseFragment<FragmentMoviesListingBinding>(),
    MovieListingMVI.View, LayoutResSupplier, ViewHolderCreator<ListItem<*>> {

    override val bindingInflater: () -> FragmentMoviesListingBinding = {
        FragmentMoviesListingBinding.inflate(layoutInflater)
    }

    override lateinit var adapter: PagingAdapter

    override val viewModel: MovieListingMVI.Model
        get() = TODO("Not yet implemented")

    override fun onScrolledToEnd() {
        viewModel.onIntent(MovieListingMVI.Intent.LoadPage)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = PagingAdapter(
            layoutResSupplier = this,
            viewHolderCreator = this,
            onScrolledToEndListener = this,
            loadingLayoutRes = R.layout.list_item_loading,
        )
    }

    override fun getLayoutResForViewType(viewType: Int): Int {
        TODO("Not yet implemented")
    }

    override fun createViewHolderForViewType(
        viewType: Int,
        view: View
    ): BindingViewHolder<*, ListItem<*>> {
        TODO("Not yet implemented")
    }
}