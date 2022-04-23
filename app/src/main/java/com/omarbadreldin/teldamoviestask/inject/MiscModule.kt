package com.omarbadreldin.teldamoviestask.inject

import android.content.Context
import android.content.res.Resources
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object MiscModule {

    @Provides
    fun provideResources(@ApplicationContext context: Context): Resources = context.resources
}