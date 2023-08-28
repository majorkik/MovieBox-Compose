package com.majorkik.tmdb.api.usecase

import arrow.core.Either
import com.majorkik.tmdb.api.model.MovieDetails
import com.majorkik.tmdb.api.repository.MovieDetailsError

fun interface GetMovieDetailsByIdUseCase : suspend (Int) -> Either<MovieDetailsError, MovieDetails>
