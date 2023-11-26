package com.majorkik.tmdb.data

import com.majorkik.tmdb.data.di.TmdbDataComponent
import com.majorkik.tmdb.data.di.TmdbDataDependencies

object TmdbDataFeature {

    lateinit var dataDependencies: TmdbDataDependencies

    private var dataComponentHolder: TmdbDataComponent? = null

    fun getApi(): TmdbDataApi = component().api

    internal fun component(): TmdbDataComponent = dataComponentHolder ?: run {
        dataComponentHolder = TmdbDataComponent(dependencies = dataDependencies)

        requireNotNull(dataComponentHolder)
    }

    internal fun reset() {
        dataComponentHolder = null
    }
}
