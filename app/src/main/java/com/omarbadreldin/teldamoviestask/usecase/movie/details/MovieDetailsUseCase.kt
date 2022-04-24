package com.omarbadreldin.teldamoviestask.usecase.movie.details

import com.omarbadreldin.base.usecase.UseCase
import com.omarbadreldin.teldamoviestask.data.model.movie.details.MovieDetails

interface MovieDetailsUseCase : UseCase<MovieIdParams, MovieDetails>