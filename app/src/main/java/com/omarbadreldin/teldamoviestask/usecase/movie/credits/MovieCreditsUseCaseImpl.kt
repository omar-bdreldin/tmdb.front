package com.omarbadreldin.teldamoviestask.usecase.movie.credits

import com.omarbadreldin.base.data.model.paramOf
import com.omarbadreldin.teldamoviestask.data.api.Params
import com.omarbadreldin.teldamoviestask.data.api.movie.GetMovieCreditsApi
import com.omarbadreldin.teldamoviestask.data.model.credits.Credits
import com.omarbadreldin.teldamoviestask.usecase.movie.details.MovieIdParams
import javax.inject.Inject

class MovieCreditsUseCaseImpl @Inject constructor(
    private val getMovieCreditsApi: GetMovieCreditsApi
): MovieCreditsUseCase {

    override suspend fun get(params: MovieIdParams): Credits {
        return getMovieCreditsApi.call(
            pathParams = listOf(
                Params.Keys.PARAM_MOVIE_ID paramOf params.movieId
            )
        ).getOrThrow()
    }
}