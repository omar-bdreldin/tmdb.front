package com.omarbadreldin.teldamoviestask.usecase.movie.search

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.omarbadreldin.teldamoviestask.usecase.movie.details.MovieIdParams
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class SearchMoviesUseCaseTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var searchMoviesUseCase: SearchMoviesUseCase

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun searchMoviesWithKeyword_responseIsValid() = runBlocking {
        val keyword = "spider"
        val movies = searchMoviesUseCase.get(
            SearchMoviesParam(keyword)
        ).results
        assert(movies.any { it.title?.contains(keyword, true) == true })
    }
}