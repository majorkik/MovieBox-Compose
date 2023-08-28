package com.majorkik.tmdb.api.model

data class PagedMovieResult(
    val movies: List<Movie>,
    val page: Int,
    val totalPages: Int,
    val totalItems: Int
)
