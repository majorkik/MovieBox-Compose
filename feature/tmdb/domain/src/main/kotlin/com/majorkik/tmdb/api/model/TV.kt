package com.majorkik.tmdb.api.model

import com.majorkik.tmdb.api.model.image.Backdrop
import com.majorkik.tmdb.api.model.image.Poster
import com.soywiz.klock.Date

data class TV(
    val adult: Boolean,
    val backdrop: Backdrop?,
    val genreIds: List<Int>,
    val id: Int,
    val originCountry: List<String>,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val poster: Poster?,
    val releaseDate: Date?,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int
)
