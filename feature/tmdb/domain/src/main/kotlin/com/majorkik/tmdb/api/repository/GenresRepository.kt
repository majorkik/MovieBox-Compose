package com.majorkik.tmdb.api.repository

import com.majorkik.tmdb.api.model.Genre
import com.majorkik.tmdb.api.network.NetworkResult

interface GenresRepository {
    suspend fun getMovieGenres(): NetworkResult<List<Genre>, String>

    suspend fun getTVGenres(): NetworkResult<List<Genre>, String>
}
