package com.majorkik.tmdb.data.di

import com.majorkik.common.di.uiLazy
import com.majorkik.tmdb.api.repository.TVsRepository
import com.majorkik.tmdb.data.TmdbDataApi
import com.majorkik.tmdb.data.TmdbDataApiImp
import com.majorkik.tmdb.data.network.ApiService
import com.majorkik.tmdb.data.network.KtorApiService
import com.majorkik.tmdb.data.network.KtorApiServiceImpl
import com.majorkik.tmdb.data.network.createApiService
import com.majorkik.tmdb.data.repository.TVsRepositoryImpl
import io.ktor.client.engine.okhttp.OkHttpConfig
import io.ktor.client.engine.okhttp.OkHttpEngine

internal interface TmdbDataModule {

    val api: TmdbDataApi
    val apiRetrofitService: ApiService
    val apiKtorService: KtorApiService
    val tvDetailsRepository: TVsRepository

    class Impl : TmdbDataModule {

        override val api: TmdbDataApi by uiLazy { TmdbDataApiImp() }

        override val apiRetrofitService: ApiService = createApiService()

        override val apiKtorService: KtorApiService = KtorApiServiceImpl(engine = OkHttpEngine(OkHttpConfig()))

        override val tvDetailsRepository: TVsRepository = TVsRepositoryImpl(
            api = apiRetrofitService,
            ktorApi = apiKtorService,
        )
    }
}

interface TmdbDataDependencies
