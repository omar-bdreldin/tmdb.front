package com.omarbadreldin.teldamoviestask.data.api

import com.omarbadreldin.teldamoviestask.data.api.Params.Keys.PARAM_MOVIE_ID

object Urls {
    const val BASE_MEDIA_URL = "https://image.tmdb.org/t/p"
    const val URL_MOVIES_POPULAR = "movie/popular"
    const val URL_MOVIES_SEARCH = "search/movie"
    const val URL_MOVIE_DETAILS = "movie/{$PARAM_MOVIE_ID}"
    const val URL_SIMILAR_MOVIES = "movie/{$PARAM_MOVIE_ID}/similar"
    const val URL_MOVIE_CREDITS = "movie/{$PARAM_MOVIE_ID}/credits"
}

fun String.prependBaseMediaUrl(width: Int? = 500): String = buildString {
    append(Urls.BASE_MEDIA_URL)
    append(
        if (width == null) "/original"
        else "/w$width"
    )
    append(this@prependBaseMediaUrl)
}