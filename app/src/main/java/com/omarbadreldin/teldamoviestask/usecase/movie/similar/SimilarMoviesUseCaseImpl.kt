package com.omarbadreldin.teldamoviestask.usecase.movie.similar

import com.omarbadreldin.base.data.model.paramOf
import com.omarbadreldin.teldamoviestask.data.api.Params
import com.omarbadreldin.teldamoviestask.data.api.movie.GetSimilarMoviesApi
import com.omarbadreldin.teldamoviestask.data.model.movie.Movie
import com.omarbadreldin.teldamoviestask.usecase.movie.details.MovieIdParams
import javax.inject.Inject

private const val LIMIT_SIMILAR_MOVIES = 5

class SimilarMoviesUseCaseImpl @Inject constructor(
    private val getSimilarMoviesApi: GetSimilarMoviesApi,
) : SimilarMoviesUseCase {

    override suspend fun get(params: MovieIdParams): List<Movie> {
        return getSimilarMoviesApi.call(
            pathParams = listOf(
                Params.Keys.PARAM_MOVIE_ID paramOf params.movieId,
                Params.Keys.PARAM_ADULT paramOf false,
            )
        ).getOrThrow().results.take(LIMIT_SIMILAR_MOVIES)
    }
}