package com.omarbadreldin.teldamoviestask.ui.screen.movielisting

import android.os.Bundle
import android.text.Editable
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.inputmethod.EditorInfo
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.omarbadreldin.base.data.paging.ClearList
import com.omarbadreldin.base.data.paging.PagingAdapter
import com.omarbadreldin.base.mvi.MviFragment
import com.omarbadreldin.base.mvi.common.ErrorState
import com.omarbadreldin.base.recycler.BindingViewHolder
import com.omarbadreldin.base.recycler.LayoutResSupplier
import com.omarbadreldin.base.recycler.ListItem
import com.omarbadreldin.base.recycler.ViewHolderCreator
import com.omarbadreldin.teldamoviestask.R
import com.omarbadreldin.teldamoviestask.data.model.movie.Movie
import com.omarbadreldin.teldamoviestask.databinding.FragmentMoviesListingBinding
import com.omarbadreldin.teldamoviestask.databinding.ListItemMoviesHeaderBinding
import com.omarbadreldin.teldamoviestask.ui.common.viewholder.TextItemViewHolder
import com.omarbadreldin.teldamoviestask.ui.dialog.showDialog
import com.omarbadreldin.teldamoviestask.ui.screen.moviedetails.MovieDetailsFragmentArgs
import com.omarbadreldin.teldamoviestask.ui.screen.movielisting.viewholder.MovieViewHolder
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
            ViewTypes.TYPE_POPULAR_MOVIE -> R.layout.list_item_movie
            ViewTypes.TYPE_HEADER -> R.layout.list_item_movies_header
            else -> throw IllegalArgumentException()
        }
    }

    override fun createViewHolderForViewType(
        viewType: Int,
        view: View
    ): BindingViewHolder<*, *> {
        return when (viewType) {
            ViewTypes.TYPE_POPULAR_MOVIE -> MovieViewHolder(
                itemView = view,
                onMovieClick = ::onMovieClick
            )
            ViewTypes.TYPE_HEADER -> TextItemViewHolder(
                itemView = view,
                binder = ListItemMoviesHeaderBinding::bind,
                textViewProvider = ListItemMoviesHeaderBinding::textView
            )
            else -> throw IllegalArgumentException()
        }
    }

    private fun onMovieClick(item: ListItem<Movie>, position: Int) {
        navController.navigate(
            R.id.action_movies_listing_fragment_to_movie_details_fragment,
            MovieDetailsFragmentArgs(item.data).toBundle()
        )
    }

    override fun setupViews() {
        setupRecyclerView()
        setupSearchTextInput()
    }

    private fun setupSearchTextInput() {
        binding.textInputEditTextSearch.apply {
//            addTextChangedListener(afterTextChanged = ::onSearchInputChange)
            setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    viewModel.onIntent(
                        MovieListingMVI.Intent.Search(binding.textInputEditTextSearch.text.toString())
                    )
                    true
                } else false
            }
        }
        binding.textInputLayoutSearch.setEndIconOnClickListener {
            binding.textInputEditTextSearch.setText("")
            viewModel.onIntent(MovieListingMVI.Intent.ResetPaging)
            startPaging()
        }
    }

    private fun onSearchInputChange(searchInput: Editable?) {
        if (searchInput.isNullOrEmpty()) {
            viewModel.onIntent(MovieListingMVI.Intent.ResetPaging)
            startPaging()
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            adapter = this@MovieListingFragment.adapter
        }
    }

    override fun setup() {
        startPaging()
    }

    override fun render(state: MovieListingMVI.State) {
        println("@omar: $state")
        when (state) {
            is ErrorState -> state.showDialog(requireContext())
            is MovieListingMVI.State.EmptyPage -> onEmptyPage(state)
            else -> super.render(state)
        }
    }

    private fun onEmptyPage(state: MovieListingMVI.State.EmptyPage) {
        binding.layoutEmptyPage.apply {
            textView.text = getString(R.string.label_no_result_are_found, state.keyword)
            root.visibility = VISIBLE
        }
    }

    override fun onClearList(state: ClearList) {
        super.onClearList(state)
        binding.layoutEmptyPage.root.visibility = GONE
    }
}