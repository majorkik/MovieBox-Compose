package com.majorkik.tmdb.api.usecase

import arrow.core.Either
import com.majorkik.tmdb.api.model.MovieDetails
import com.majorkik.tmdb.api.repository.MovieDetailsError
import com.majorkik.tmdb.api.repository.MovieDetailsRepository

interface GetMovieDetailsByIdUseCase {
    suspend fun invoke(id: Int): Either<MovieDetailsError, MovieDetails>
}

internal class GetMovieDetailsByIdUseCaseImpl(
    private val repository: MovieDetailsRepository,
) : GetMovieDetailsByIdUseCase {

    override suspend fun invoke(id: Int): Either<MovieDetailsError, MovieDetails> =
        repository.getMovieDetailsById(id = id)
}
