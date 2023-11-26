package com.majorkik.movieboxcompose.di

import androidx.annotation.MainThread
import com.majorkik.tmdb.data.TmdbDataFeature
import com.majorkik.tmdb.data.di.TmdbDataDependencies

class TmdbDataContainer {
    init {
        TmdbDataFeature.dataDependencies = createDependencies()
    }

    @MainThread
    fun getApi() = TmdbDataFeature.getApi()

    private fun createDependencies() = object : TmdbDataDependencies {}
}
