package com.omarbadreldin.teldamoviestask.usecase.movie.credits

import com.omarbadreldin.base.usecase.UseCase
import com.omarbadreldin.teldamoviestask.data.model.credits.Cast
import com.omarbadreldin.teldamoviestask.usecase.movie.details.MovieIdParams

interface MovieCreditsUseCase : UseCase<MovieIdParams, List<Cast>>