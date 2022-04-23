package com.omarbadreldin.teldamoviestask.ui.listing

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.omarbadreldin.base.data.paging.PagingAdapter
import com.omarbadreldin.base.mvi.MviFragment
import com.omarbadreldin.base.recycler.BindingViewHolder
import com.omarbadreldin.base.recycler.LayoutResSupplier
import com.omarbadreldin.base.recycler.ViewHolderCreator
import com.omarbadreldin.teldamoviestask.R
import com.omarbadreldin.teldamoviestask.databinding.FragmentMoviesListingBinding
import com.omarbadreldin.teldamoviestask.ui.listing.viewholder.MovieViewHolder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieListingFragment :
    MviFragment<FragmentMoviesListingBinding, MovieListingMVI.Intent, MovieListingMVI.State, MovieListingViewModel>(),
    MovieListingMVI.View,
    LayoutResSupplier,
    ViewHolderCreator {

    override val bindingInflater: () -> FragmentMoviesListingBinding = {
        FragmentMoviesListingBinding.inflate(layoutInflater)
    }

    override lateinit var adapter: PagingAdapter

    override val recyclerView: RecyclerView
        get() = binding.recyclerView

    override val viewModel: MovieListingViewModel by viewModels()

    override fun onScrolledToEnd() {
        viewModel.onIntent(MovieListingMVI.Intent.LoadPage)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = createAdapter()
    }

    override fun createAdapter(): PagingAdapter {
        return PagingAdapter(
            layoutResSupplier = this,
            viewHolderCreator = this,
            onScrolledToEndListener = this,
            loadingLayoutRes = R.layout.list_item_loading,
        )
    }

    override fun getLayoutResForViewType(viewType: Int): Int {
        return when (viewType) {
            ViewTypes.TYPE_MOVIE -> R.layout.list_item_movie
            else -> throw IllegalArgumentException()
        }
    }

    override fun createViewHolderForViewType(
        viewType: Int,
        view: View
    ): BindingViewHolder<*, *> {
        return when (viewType) {
            ViewTypes.TYPE_MOVIE -> MovieViewHolder(view)
            else -> throw IllegalArgumentException()
        }
    }

    override fun setupViews() {
        binding.recyclerView.apply {
            adapter = this@MovieListingFragment.adapter
        }
    }

    override fun setup() {
        startPaging()
    }
}