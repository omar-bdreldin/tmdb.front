package com.omarbadreldin.teldamoviestask.data.datasource.movie

import com.omarbadreldin.base.data.model.intParamOf
import com.omarbadreldin.base.data.model.paramOf
import com.omarbadreldin.base.data.paging.BasePagingDataSource
import com.omarbadreldin.teldamoviestask.data.api.Params
import com.omarbadreldin.teldamoviestask.data.api.movie.GetPopularMoviesApi
import com.omarbadreldin.teldamoviestask.data.api.movie.SearchMoviesApi
import com.omarbadreldin.teldamoviestask.data.model.movie.Movie
import com.omarbadreldin.teldamoviestask.data.model.response.paging.PagingResponse
import com.omarbadreldin.teldamoviestask.data.model.response.paging.hasReachedEnd
import javax.inject.Inject

class SearchMoviesDataSource @Inject constructor(
    api: SearchMoviesApi,
) : BasePagingDataSource<Movie, PagingResponse<Movie>>(
    PagingResponse<Movie>::hasReachedEnd,
    Params.Keys.PARAM_PAGE intParamOf 1,
    Params.Keys.PARAM_PER_PAGE intParamOf 12,
    api
) {

    suspend fun get(query: String): PagingResponse<Movie> {
        return api.call(
            queryParams = listOf(
                pagingKey,
                Params.Keys.PARAM_QUERY paramOf query,
                Params.Keys.PARAM_ADULT paramOf false,
            )
        ).getOrThrow()
    }

    override suspend fun getActual(): PagingResponse<Movie> {
        return api.call(
            queryParams = listOf(
                pagingKey,
            )
        ).getOrThrow()
    }

    override fun reset() {
        pagingKey = pagingKey.copy(
            value = 1
        )
    }
}