package com.omarbadreldin.teldamoviestask.data.api

import com.omarbadreldin.teldamoviestask.data.api.Params.Keys.PARAM_MOVIE_ID

object Urls {
    const val BASE_MEDIA_URL = "https://image.tmdb.org/t/p"
    const val URL_MOVIES_POPULAR = "movie/popular"
    const val URL_MOVIES_SEARCH = "search/movie"
    const val URL_MOVIE_DETAILS = "movie/{$PARAM_MOVIE_ID}"
}