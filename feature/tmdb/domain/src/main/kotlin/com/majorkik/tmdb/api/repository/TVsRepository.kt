package com.majorkik.tmdb.api.repository

import com.majorkik.tmdb.api.model.PagedTVsResult
import com.majorkik.tmdb.api.network.NetworkResult

interface TVsRepository {
    suspend fun getPopularTVs(page: Int): NetworkResult<PagedTVsResult, String>

    suspend fun getTrendingTVs(page: Int): NetworkResult<PagedTVsResult, String>
}
