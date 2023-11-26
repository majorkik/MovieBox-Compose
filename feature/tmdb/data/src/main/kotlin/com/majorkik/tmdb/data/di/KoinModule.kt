package com.majorkik.tmdb.data.di

import com.majorkik.tmdb.api.repository.GenresRepository
import com.majorkik.tmdb.api.repository.MovieDetailsRepository
import com.majorkik.tmdb.api.repository.MoviesRepository
import com.majorkik.tmdb.api.repository.TVsRepository
import com.majorkik.tmdb.data.network.KtorApiService
import com.majorkik.tmdb.data.network.KtorApiServiceImpl
import com.majorkik.tmdb.data.network.createApiService
import com.majorkik.tmdb.data.repository.GenresRepositoryImpl
import com.majorkik.tmdb.data.repository.MovieDetailsRepositoryImpl
import com.majorkik.tmdb.data.repository.MoviesRepositoryImpl
import com.majorkik.tmdb.data.repository.TVsRepositoryImpl
import io.ktor.client.engine.okhttp.OkHttpConfig
import io.ktor.client.engine.okhttp.OkHttpEngine
import org.koin.dsl.module

val tmdbImplModule = module {
    // API service
    single { createApiService() }
    single<KtorApiService> { KtorApiServiceImpl(engine = OkHttpEngine(OkHttpConfig())) }

    // Repositories
    single<MovieDetailsRepository> { MovieDetailsRepositoryImpl(api = get()) }
    single<GenresRepository> { GenresRepositoryImpl(api = get()) }
    single<MoviesRepository> { MoviesRepositoryImpl(api = get()) }
    single<TVsRepository> { TVsRepositoryImpl(api = get(), ktorApi = get()) }
}
