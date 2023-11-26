package com.majorkik.tmdb.api.usecase

import arrow.core.Either
import com.majorkik.tmdb.api.model.MovieDetails
import com.majorkik.tmdb.api.repository.MovieDetailsError
import com.majorkik.tmdb.api.repository.MovieDetailsRepository

interface GetMovieDetailsUseCase {
    suspend fun invoke(id: Int): Either<MovieDetailsError, MovieDetails>
}

internal class GetMovieDetailsUseCaseImpl(
    private val repository: MovieDetailsRepository,
) : GetMovieDetailsUseCase {

    override suspend fun invoke(id: Int): Either<MovieDetailsError, MovieDetails> =
        repository.getMovieDetailsById(id = id)
}
