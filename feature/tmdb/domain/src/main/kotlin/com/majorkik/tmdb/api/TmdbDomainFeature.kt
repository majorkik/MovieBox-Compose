package com.majorkik.tmdb.api

import com.majorkik.tmdb.api.di.TmdbDomainComponent
import com.majorkik.tmdb.api.di.TmdbDomainDependencies

object TmdbDomainFeature {

    lateinit var domainDependencies: TmdbDomainDependencies

    private var detailsComponentHolder: TmdbDomainComponent? = null

    fun getApi(): TmdbDomainApi = component().api

    internal fun component(): TmdbDomainComponent = detailsComponentHolder ?: run {
        detailsComponentHolder = TmdbDomainComponent(dependencies = domainDependencies)

        requireNotNull(detailsComponentHolder)
    }

    internal fun reset() {
        detailsComponentHolder = null
    }
}
