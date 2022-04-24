package com.omarbadreldin.teldamoviestask.usecase.movie.similar

import com.omarbadreldin.base.usecase.UseCase
import com.omarbadreldin.teldamoviestask.data.model.movie.Movie
import com.omarbadreldin.teldamoviestask.usecase.movie.details.MovieIdParams

interface SimilarMoviesUseCase : UseCase<MovieIdParams, List<Movie>>