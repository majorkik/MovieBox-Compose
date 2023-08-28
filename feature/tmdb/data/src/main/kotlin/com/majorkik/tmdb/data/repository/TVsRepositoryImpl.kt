package com.majorkik.tmdb.data.repository

import com.majorkik.tmdb.api.model.PagedTVsResult
import com.majorkik.tmdb.api.model.TVDetails
import com.majorkik.tmdb.api.network.NetworkResult
import com.majorkik.tmdb.api.repository.TVsRepository
import com.majorkik.tmdb.data.network.ApiService
import com.majorkik.tmdb.data.network.KtorApiService
import com.majorkik.tmdb.data.network.safeRequest
import com.majorkik.tmdb.data.network.suspendString
import com.majorkik.tmdb.data.response.toDomain
import com.majorkik.tmdb.data.response.toDomainModel

internal class TVsRepositoryImpl(
    private val api: ApiService,
    private val ktorApi: KtorApiService,
) : TVsRepository {

    override suspend fun getPopularTVs(page: Int): NetworkResult<PagedTVsResult, String> {
        return safeRequest(
            call = { api.getPopularTVs(page = page) },
            onSuccess = { it?.toDomainModel() },
            onError = { it?.suspendString() ?: "Error when getting a list of popular TV shows" }
        )
    }

    override suspend fun getTrendingTVs(page: Int): NetworkResult<PagedTVsResult, String> {
        return safeRequest(
            call = { api.getTrendingTVs(page = page) },
            onSuccess = { it?.toDomainModel() },
            onError = { it?.suspendString() ?: "Error when getting a list of trending TV shows" }
        )
    }

    override suspend fun getTV(tvId: Int): TVDetails = ktorApi.getTV(tvId = tvId).toDomain()
}
