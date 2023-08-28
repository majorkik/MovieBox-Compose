package com.majorkik.tmdb.api.repository

import com.majorkik.tmdb.api.model.PagedMovieResult
import com.majorkik.tmdb.api.network.NetworkResult

interface MoviesRepository {
    suspend fun getPopularMovies(page: Int): NetworkResult<PagedMovieResult, String>

    suspend fun getTrendingMovies(page: Int): NetworkResult<PagedMovieResult, String>
}
