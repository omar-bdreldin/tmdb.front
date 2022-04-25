package com.omarbadreldin.teldamoviestask.usecase.movie.search

import com.omarbadreldin.base.data.model.intParamOf
import com.omarbadreldin.base.data.model.paramOf
import com.omarbadreldin.base.data.paging.BasePagingUseCase
import com.omarbadreldin.teldamoviestask.data.api.Params
import com.omarbadreldin.teldamoviestask.data.api.movie.SearchMoviesApi
import com.omarbadreldin.teldamoviestask.data.model.movie.Movie
import com.omarbadreldin.teldamoviestask.data.model.response.paging.PagingResponse
import com.omarbadreldin.teldamoviestask.data.model.response.paging.hasReachedEnd
import javax.inject.Inject

class SearchMoviesUseCase @Inject constructor(
    api: SearchMoviesApi,
) : BasePagingUseCase<SearchMoviesParam, Movie, PagingResponse<Movie>>(
    PagingResponse<Movie>::hasReachedEnd,
    Params.Keys.PARAM_PAGE intParamOf 1,
    Params.Keys.PARAM_PER_PAGE intParamOf 12,
    api
) {

    override suspend fun getActual(params: SearchMoviesParam): PagingResponse<Movie> {
        return api.call(
            queryParams = listOf(
                pagingKey,
                Params.Keys.PARAM_QUERY paramOf params.query,
                Params.Keys.PARAM_ADULT paramOf false,
            )
        ).getOrThrow()
    }

    override fun reset() {
        pagingKey = pagingKey.copy(
            value = 1
        )
    }
}