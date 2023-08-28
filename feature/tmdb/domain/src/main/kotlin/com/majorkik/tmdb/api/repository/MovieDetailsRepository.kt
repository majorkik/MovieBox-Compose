package com.majorkik.tmdb.api.repository

import arrow.core.Either
import com.majorkik.tmdb.api.model.MovieDetails

interface MovieDetailsRepository {
    suspend fun getMovieDetailsById(id: Int): Either<MovieDetailsError, MovieDetails>
}

sealed class MovieDetailsError {
    object NetworkFailure : MovieDetailsError()
    object HttpFailure : MovieDetailsError()
    object ApiFailure : MovieDetailsError()
    object Unknown : MovieDetailsError()
}
