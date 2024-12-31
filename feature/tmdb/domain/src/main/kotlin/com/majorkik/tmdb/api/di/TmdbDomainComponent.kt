package com.majorkik.tmdb.api.di

import com.majorkik.tmdb.api.TmdbDomainApi
import com.majorkik.tmdb.api.usecase.GetMovieDetailsUseCase
import com.majorkik.tmdb.api.usecase.GetTVDetailsUseCase

internal interface TmdbDomainComponent {

    val api: TmdbDomainApi
    val getTVDetailsUseCase: GetTVDetailsUseCase
    val getMovieDetailsUseCase: GetMovieDetailsUseCase
}

internal fun TmdbDomainComponent(dependencies: TmdbDomainDependencies): TmdbDomainComponent {
    val detailsModule = TmdbDomainModule.Impl(dependencies = dependencies)

    return object : TmdbDomainComponent, TmdbDomainModule by detailsModule {}
}