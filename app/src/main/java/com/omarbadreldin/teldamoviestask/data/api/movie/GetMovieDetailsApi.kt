package com.omarbadreldin.teldamoviestask.data.api.movie

import com.omarbadreldin.base.data.api.KtorApi
import com.omarbadreldin.teldamoviestask.data.api.Urls
import com.omarbadreldin.teldamoviestask.data.model.movie.details.MovieDetails
import io.ktor.client.*
import io.ktor.util.reflect.*
import javax.inject.Inject

class GetMovieDetailsApi @Inject constructor(client: HttpClient) : KtorApi<MovieDetails>(
    client = client,
    url = Urls.URL_MOVIE_DETAILS,
    typeInfo = typeInfo<MovieDetails>()
)