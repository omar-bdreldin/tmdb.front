package com.omarbadreldin.teldamoviestask.data.api.movie

import com.omarbadreldin.base.data.api.KtorApi
import com.omarbadreldin.teldamoviestask.data.api.Urls
import com.omarbadreldin.teldamoviestask.data.model.response.credits.CreditsResponse
import io.ktor.client.*
import io.ktor.util.reflect.*
import javax.inject.Inject

class GetMovieCreditsApi @Inject constructor(client: HttpClient) : KtorApi<CreditsResponse>(
    client = client,
    url = Urls.URL_MOVIE_DETAILS,
    typeInfo = typeInfo<CreditsResponse>()
)