package com.omarbadreldin.teldamoviestask.inject

import com.omarbadreldin.teldamoviestask.BuildConfig
import com.omarbadreldin.teldamoviestask.data.api.Params
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideHttpClient(): HttpClient {
        return HttpClient(CIO) {
            install(ContentNegotiation) {
                json()
            }
            defaultRequest {
                url(urlString = BuildConfig.BASE_URL)
                url {
                    parameters.append(Params.Keys.PARAM_API_KEY, BuildConfig.TMDB_API_KEY)
                }
            }
        }.apply {
            plugin(HttpSend).intercept { request ->
                println(request.url.buildString())
                execute(request)
            }
        }
    }
}