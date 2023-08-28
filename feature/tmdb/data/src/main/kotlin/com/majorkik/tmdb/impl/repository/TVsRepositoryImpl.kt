package com.majorkik.tmdb.impl.repository

import com.majorkik.tmdb.api.model.PagedTVsResult
import com.majorkik.tmdb.api.model.TV
import com.majorkik.tmdb.api.network.NetworkResult
import com.majorkik.tmdb.api.repository.TVsRepository
import com.majorkik.tmdb.impl.network.ApiService
import com.majorkik.tmdb.impl.network.safeRequest
import com.majorkik.tmdb.impl.network.suspendString
import com.majorkik.tmdb.impl.response.toDomainModel

internal class TVsRepositoryImpl(private val api: ApiService) : TVsRepository {
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

    suspend fun getTVShow(tvId: Int): TV {
        return api.getTV(id = tvId).toDomainModel()
    }
}
