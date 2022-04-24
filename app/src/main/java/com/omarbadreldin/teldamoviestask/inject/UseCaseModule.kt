package com.omarbadreldin.teldamoviestask.inject

import com.omarbadreldin.teldamoviestask.usecase.movie.details.MovieDetailsUseCase
import com.omarbadreldin.teldamoviestask.usecase.movie.details.MovieDetailsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun bindMovieDetailsUseCase(useCase: MovieDetailsUseCaseImpl): MovieDetailsUseCase
}