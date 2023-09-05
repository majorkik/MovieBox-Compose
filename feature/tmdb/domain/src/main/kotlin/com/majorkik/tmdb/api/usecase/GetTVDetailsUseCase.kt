package com.majorkik.tmdb.api.usecase

import com.majorkik.tmdb.api.model.TVDetails

fun interface GetTVDetailsUseCase : suspend (Int) -> TVDetails
