package com.majorkik.ui.details.ui.di

import com.majorkik.common.di.uiLazy
import com.majorkik.tmdb.api.usecase.GetMovieDetailsUseCase
import com.majorkik.tmdb.api.usecase.GetTVDetailsUseCase
import com.majorkik.ui.details.DetailsApi
import com.majorkik.ui.details.DetailsApiImpl
import com.majorkik.ui.details.ui.movie.MovieDetailsViewModel
import com.majorkik.ui.details.ui.tv.TVDetailsViewModel

internal interface DetailsModule {

    val api: DetailsApi

    fun createMovieDetailsViewModel(movieId: Int): MovieDetailsViewModel
    fun createTVDetailsViewModel(tvId: Int): TVDetailsViewModel

    class Impl(
        private val dependencies: DetailsDependencies,
    ) : DetailsModule {

        override val api: DetailsApi by uiLazy { DetailsApiImpl() }

        override fun createMovieDetailsViewModel(movieId: Int): MovieDetailsViewModel = MovieDetailsViewModel(
            movieId = movieId,
            getMovieDetailsUseCase = dependencies.getMovieDetailsUseCase
        )

        override fun createTVDetailsViewModel(
            tvId: Int,
        ): TVDetailsViewModel = TVDetailsViewModel(
            tvId = tvId,
            getTVDetailsUseCase = dependencies.getTVDetailsUseCase,
        )
    }
}

interface DetailsDependencies {
    val getTVDetailsUseCase: GetTVDetailsUseCase
    val getMovieDetailsUseCase: GetMovieDetailsUseCase
}
