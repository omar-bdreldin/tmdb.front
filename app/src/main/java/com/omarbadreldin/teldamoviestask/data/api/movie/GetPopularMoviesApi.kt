package com.omarbadreldin.teldamoviestask.data.api.movie

import com.omarbadreldin.base.data.api.KtorApi
import com.omarbadreldin.teldamoviestask.data.api.Urls
import com.omarbadreldin.teldamoviestask.data.model.movie.Movie
import com.omarbadreldin.teldamoviestask.data.model.response.paging.PagingResponse
import io.ktor.client.*
import io.ktor.util.reflect.*
import javax.inject.Inject

class GetPopularMoviesApi @Inject constructor(
    client: HttpClient,
) : KtorApi<PagingResponse<Movie>>(
    client = client,
    url = Urls.URL_MOVIES_POPULAR,
    typeInfo = typeInfo<PagingResponse<Movie>>()
)