package com.majorkik.tmdb.api.model

data class PagedTVsResult(
    val tvs: List<TV>,
    val page: Int,
    val totalPages: Int,
    val totalItems: Int
)
