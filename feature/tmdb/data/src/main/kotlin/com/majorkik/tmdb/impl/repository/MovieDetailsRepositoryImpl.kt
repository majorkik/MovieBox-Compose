package com.majorkik.tmdb.impl.repository

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.majorkik.tmdb.api.model.MovieDetails
import com.majorkik.tmdb.api.repository.MovieDetailsError
import com.majorkik.tmdb.api.repository.MovieDetailsRepository
import com.majorkik.tmdb.impl.network.ApiService
import com.majorkik.tmdb.impl.response.toDomainModel
import com.majorkik.tmdb.impl.util.printLog
import com.slack.eithernet.ApiResult

internal class MovieDetailsRepositoryImpl(private val api: ApiService) : MovieDetailsRepository {
    override suspend fun getMovieDetailsById(id: Int): Either<MovieDetailsError, MovieDetails> {
        return when (val response = api.getMovieById(id = id, appendToResponse = "images,credits")) {
            is ApiResult.Success -> response.value.toDomainModel().right()
            is ApiResult.Failure -> {
                response.printLog()

                when (response) {
                    is ApiResult.Failure.NetworkFailure -> MovieDetailsError.NetworkFailure
                    is ApiResult.Failure.UnknownFailure -> MovieDetailsError.Unknown
                    is ApiResult.Failure.HttpFailure -> MovieDetailsError.HttpFailure
                    is ApiResult.Failure.ApiFailure -> MovieDetailsError.ApiFailure
                }.left()
            }
        }
    }
}
