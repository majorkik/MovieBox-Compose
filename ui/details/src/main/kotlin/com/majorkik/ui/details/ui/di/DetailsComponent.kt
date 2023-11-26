package com.majorkik.ui.details.ui.di

import com.majorkik.ui.details.DetailsApi
import com.majorkik.ui.details.ui.movie.MovieDetailsViewModel
import com.majorkik.ui.details.ui.tv.TVDetailsViewModel

internal interface DetailsComponent {

    val api: DetailsApi

    fun createTVDetailsViewModel(tvId: Int): TVDetailsViewModel
    fun createMovieDetailsViewModel(movieId: Int): MovieDetailsViewModel
}

internal fun DetailsComponent(dependencies: DetailsDependencies): DetailsComponent {
    val detailsModule = DetailsModule.Impl(dependencies = dependencies)

    return object : DetailsComponent, DetailsModule by detailsModule {}
}
