package com.majorkik.tmdb.api.model

import com.majorkik.tmdb.api.model.image.Backdrop
import com.majorkik.tmdb.api.model.image.Poster
import com.soywiz.klock.Date

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val releaseDate: Date?,
    val poster: Poster?,
    val backdrop: Backdrop?,
    val originalLanguage: String,
    val originalTitle: String,
    val popularity: Double,
    val voteAverage: Double,
    val voteCount: Int,
    val genreIds: List<Int>,
    val adult: Boolean,
    val video: Boolean,
)
