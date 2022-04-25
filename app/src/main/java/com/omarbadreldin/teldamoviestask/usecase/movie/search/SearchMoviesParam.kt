package com.omarbadreldin.teldamoviestask.usecase.movie.search

import com.omarbadreldin.base.usecase.Params

@JvmInline
value class SearchMoviesParam(val query: String) : Params