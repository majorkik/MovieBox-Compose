package com.majorkik.tmdb.data.di

import com.majorkik.tmdb.api.repository.TVsRepository
import com.majorkik.tmdb.data.TmdbDataApi

internal interface TmdbDataComponent {

    val api: TmdbDataApi
    val tvDetailsRepository: TVsRepository
}

@Suppress("UnusedPrivateMember")
internal fun TmdbDataComponent(dependencies: TmdbDataDependencies): TmdbDataComponent {
    val detailsModule = TmdbDataModule.Impl()

    return object : TmdbDataComponent, TmdbDataModule by detailsModule {}
}
