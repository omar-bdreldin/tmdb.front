package com.omarbadreldin.teldamoviestask.inject

import com.omarbadreldin.teldamoviestask.usecase.movie.credits.MovieCreditsUseCase
import com.omarbadreldin.teldamoviestask.usecase.movie.credits.MovieCreditsUseCaseImpl
import com.omarbadreldin.teldamoviestask.usecase.movie.details.MovieDetailsUseCase
import com.omarbadreldin.teldamoviestask.usecase.movie.details.MovieDetailsUseCaseImpl
import com.omarbadreldin.teldamoviestask.usecase.movie.similar.SimilarMoviesUseCase
import com.omarbadreldin.teldamoviestask.usecase.movie.similar.SimilarMoviesUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun bindMovieDetailsUseCase(useCase: MovieDetailsUseCaseImpl): MovieDetailsUseCase

    @Binds
    abstract fun bindSimilarMoviesUseCase(useCase: SimilarMoviesUseCaseImpl): SimilarMoviesUseCase

    @Binds
    abstract fun bindMovieCreditsUseCase(useCase: MovieCreditsUseCaseImpl): MovieCreditsUseCase
}