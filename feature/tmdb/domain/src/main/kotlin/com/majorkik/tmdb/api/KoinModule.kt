package com.majorkik.tmdb.api

import com.majorkik.tmdb.api.repository.MovieDetailsRepository
import com.majorkik.tmdb.api.repository.TVsRepository
import com.majorkik.tmdb.api.usecase.GetMovieDetailsByIdUseCase
import com.majorkik.tmdb.api.usecase.GetTVDetailsUseCase
import org.koin.dsl.module

val tmdbApiModule = module {
    single {
        GetMovieDetailsByIdUseCase(get<MovieDetailsRepository>()::getMovieDetailsById)
        GetTVDetailsUseCase(get<TVsRepository>()::getTV)
    }
}
