package com.majorkik.tmdb.api.usecase

import com.majorkik.tmdb.api.model.TVDetails
import com.majorkik.tmdb.api.repository.TVsRepository

interface GetTVDetailsUseCase {
    suspend fun invoke(id: Int): TVDetails
}

internal class GetTVDetailsUseCaseImpl(private val repository: TVsRepository) : GetTVDetailsUseCase {

    override suspend fun invoke(id: Int): TVDetails = repository.getTV(tvId = id)
}
