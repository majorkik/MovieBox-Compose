package com.majorkik.tmdb.api

import com.majorkik.tmdb.api.usecase.GetMovieDetailsByIdUseCase
import com.majorkik.tmdb.api.usecase.GetMovieDetailsByIdUseCaseImpl
import com.majorkik.tmdb.api.usecase.GetTVDetailsUseCase
import com.majorkik.tmdb.api.usecase.GetTVDetailsUseCaseImpl
import org.koin.dsl.module

val tmdbApiModule = module {
    single<GetMovieDetailsByIdUseCase> { GetMovieDetailsByIdUseCaseImpl(repository = get()) }
    single<GetTVDetailsUseCase> { GetTVDetailsUseCaseImpl(repository = get()) }
}
