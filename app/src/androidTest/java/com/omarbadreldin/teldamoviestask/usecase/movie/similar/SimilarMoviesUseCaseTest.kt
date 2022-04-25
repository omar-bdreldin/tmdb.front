package com.omarbadreldin.teldamoviestask.usecase.movie.similar

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.omarbadreldin.teldamoviestask.usecase.movie.details.MovieIdParams
import com.omarbadreldin.teldamoviestask.usecase.movie.similar.SimilarMoviesUseCase
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class SimilarMoviesUseCaseTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var similarMoviesUseCase: SimilarMoviesUseCase

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun getSimilarMovies_validResponse() = runBlocking {
        val movies = similarMoviesUseCase.get(
            MovieIdParams(414906)
        )
        assert(movies.size == 5)
    }
}