package com.majorkik.tmdb.impl.repository

import com.majorkik.tmdb.api.model.Genre
import com.majorkik.tmdb.api.network.NetworkResult
import com.majorkik.tmdb.api.repository.GenresRepository
import com.majorkik.tmdb.impl.network.ApiService
import com.majorkik.tmdb.impl.network.safeRequest
import com.majorkik.tmdb.impl.response.toDomainModel

internal class GenresRepositoryImpl(private val api: ApiService) : GenresRepository {
    override suspend fun getMovieGenres(): NetworkResult<List<Genre>, String> {
        return safeRequest(
            call = { api.getMovieGenres() },
            onSuccess = { it?.toDomainModel() },
            onError = { "Error" }
        )
    }

    override suspend fun getTVGenres(): NetworkResult<List<Genre>, String> {
        return safeRequest(
            call = { api.getTVGenres() },
            onSuccess = { it?.toDomainModel() },
            onError = { "Error" }
        )
    }
}
