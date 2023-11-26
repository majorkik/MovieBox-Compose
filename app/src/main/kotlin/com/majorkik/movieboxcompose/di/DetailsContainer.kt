package com.majorkik.movieboxcompose.di

import androidx.annotation.MainThread
import com.majorkik.movieboxcompose.di.AppContainer.tmdbDomainContainer
import com.majorkik.tmdb.api.usecase.GetMovieDetailsUseCase
import com.majorkik.tmdb.api.usecase.GetTVDetailsUseCase
import com.majorkik.ui.details.DetailsFeature
import com.majorkik.ui.details.ui.di.DetailsDependencies

class DetailsContainer {
    init {
        DetailsFeature.detailsDependencies = createDependencies()
    }

    @MainThread
    fun getApi() = DetailsFeature.getApi()

    private fun createDependencies() = object : DetailsDependencies {

        override val getTVDetailsUseCase: GetTVDetailsUseCase = tmdbDomainContainer.getApi().getTVDetailsUseCase()
        override val getMovieDetailsUseCase: GetMovieDetailsUseCase =
            tmdbDomainContainer.getApi().getMovieDetailsUseCase()
    }
}
