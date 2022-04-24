package com.omarbadreldin.teldamoviestask.usecase.movie.details

import com.omarbadreldin.base.data.model.paramOf
import com.omarbadreldin.teldamoviestask.data.api.Params
import com.omarbadreldin.teldamoviestask.data.api.movie.GetMovieDetailsApi
import com.omarbadreldin.teldamoviestask.data.model.movie.details.MovieDetails
import javax.inject.Inject

class MovieDetailsUseCaseImpl @Inject constructor(
    private val getMovieDetailsApi: GetMovieDetailsApi
) : MovieDetailsUseCase {

    override suspend fun get(params: MovieIdParams): MovieDetails {
        return getMovieDetailsApi.call(
            pathParams = listOf(
                Params.Keys.PARAM_MOVIE_ID paramOf params.movieId
            )
        ).getOrThrow()
    }
}