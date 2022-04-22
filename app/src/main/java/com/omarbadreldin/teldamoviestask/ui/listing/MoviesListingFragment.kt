package com.omarbadreldin.teldamoviestask.ui.listing

import com.omarbadreldin.base.fragment.BaseFragment
import com.omarbadreldin.teldamoviestask.databinding.FragmentMoviesListingBinding

class MoviesListingFragment : BaseFragment<FragmentMoviesListingBinding>() {

    override val bindingInflater: () -> FragmentMoviesListingBinding = {
        FragmentMoviesListingBinding.inflate(layoutInflater)
    }
}