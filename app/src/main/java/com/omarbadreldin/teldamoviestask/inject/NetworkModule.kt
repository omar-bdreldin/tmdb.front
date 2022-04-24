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
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideHttpClient(): HttpClient {
        return HttpClient(CIO) {
            installParser()
            installLogger()
            installTimeOut()
            setupDefaultRequest()
        }
    }
}

private fun HttpClientConfig<CIOEngineConfig>.installParser() {
    install(ContentNegotiation) {
        json(
            json = Json {
                encodeDefaults = true
                isLenient = true
                prettyPrint = false
                ignoreUnknownKeys = true
            }
        )
    }
}

private fun HttpClientConfig<CIOEngineConfig>.installLogger() {
    install(Logging) {
        logger = Logger.ANDROID
        level = LogLevel.ALL
    }
}

private const val REQUEST_TIME_OUT = (1000 * 60).toLong()

private fun HttpClientConfig<CIOEngineConfig>.installTimeOut() {
    install(HttpTimeout) {
        requestTimeoutMillis = REQUEST_TIME_OUT
    }
}

private fun HttpClientConfig<CIOEngineConfig>.setupDefaultRequest() {
    defaultRequest {
        url(urlString = "${BuildConfig.BASE_URL}/")
        url {
            parameters.append(Params.Keys.PARAM_API_KEY, BuildConfig.TMDB_API_KEY)
        }
    }
}