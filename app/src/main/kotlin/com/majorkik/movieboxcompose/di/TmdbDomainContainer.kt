package com.majorkik.movieboxcompose.di

import androidx.annotation.MainThread
import com.majorkik.movieboxcompose.di.AppContainer.tmdbDataContainer
import com.majorkik.tmdb.api.TmdbDomainFeature
import com.majorkik.tmdb.api.di.TmdbDomainDependencies
import com.majorkik.tmdb.api.repository.MovieDetailsRepository
import com.majorkik.tmdb.api.repository.TVsRepository

class TmdbDomainContainer {
    init {
        TmdbDomainFeature.domainDependencies = createDependencies()
    }

    @MainThread
    fun getApi() = TmdbDomainFeature.getApi()

    private fun createDependencies() = object : TmdbDomainDependencies {
        override fun getTVRepository(): TVsRepository {
            return tmdbDataContainer.getApi().getTVsRepository()
        }
    }
}
