package com.majorkik.movieboxcompose.di

object AppContainer {
    val tmdbDataContainer: TmdbDataContainer = TmdbDataContainer()
    val tmdbDomainContainer: TmdbDomainContainer = TmdbDomainContainer()
    val detailsContainer: DetailsContainer = DetailsContainer()
}
