package com.majorkik.tmdb.api

import com.majorkik.tmdb.api.repository.MovieDetailsRepository
import com.majorkik.tmdb.api.usecase.GetMovieDetailsByIdUseCase
import org.koin.dsl.module

val tmdbApiModule = module {
    single {
        GetMovieDetailsByIdUseCase(get<MovieDetailsRepository>()::getMovieDetailsById)
    }
}
