package com.majorkik.tmdb.api.di

import com.majorkik.common.di.uiLazy
import com.majorkik.tmdb.api.TmdbDomainApi
import com.majorkik.tmdb.api.TmdbDomainApiImp
import com.majorkik.tmdb.api.repository.MovieDetailsRepository
import com.majorkik.tmdb.api.repository.TVsRepository
import com.majorkik.tmdb.api.usecase.GetMovieDetailsUseCase
import com.majorkik.tmdb.api.usecase.GetMovieDetailsUseCaseImpl
import com.majorkik.tmdb.api.usecase.GetTVDetailsUseCase
import com.majorkik.tmdb.api.usecase.GetTVDetailsUseCaseImpl

internal interface TmdbDomainModule {

    val api: TmdbDomainApi
    val getTVDetailsUseCase: GetTVDetailsUseCase
    val getMovieDetailsUseCase: GetMovieDetailsUseCase

    class Impl(dependencies: TmdbDomainDependencies) : TmdbDomainModule {

        override val api: TmdbDomainApi by uiLazy { TmdbDomainApiImp() }

        override val getTVDetailsUseCase: GetTVDetailsUseCase =
            GetTVDetailsUseCaseImpl(repository = dependencies.getTVRepository())

        override val getMovieDetailsUseCase: GetMovieDetailsUseCase =
            GetMovieDetailsUseCaseImpl(repository = dependencies.getMovieDetailsRepository())
    }
}

interface TmdbDomainDependencies {

    fun getTVRepository(): TVsRepository
    fun getMovieDetailsRepository(): MovieDetailsRepository
}
