package com.omarbadreldin.teldamoviestask.inject

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.omarbadreldin.teldamoviestask.BuildConfig.APPLICATION_ID
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object PrefsModule {

    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("$APPLICATION_ID.prefs", MODE_PRIVATE)
    }
}