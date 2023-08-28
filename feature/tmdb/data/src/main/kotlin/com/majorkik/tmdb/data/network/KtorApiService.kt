package com.majorkik.tmdb.data.network

import com.majorkik.tmdb.data.response.TVDetailsResponse

internal interface KtorApiService {

    suspend fun getTV(tvId: Int): TVDetailsResponse
}
