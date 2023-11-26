package com.majorkik.ui.details.ui.di

import com.majorkik.ui.details.DetailsApi
import com.majorkik.ui.details.ui.tv.TVDetailsViewModel

internal interface DetailsComponent {

    val api: DetailsApi

    fun createTVDetailsViewModel(tvId: Int): TVDetailsViewModel
}

internal fun DetailsComponent(dependencies: DetailsDependencies): DetailsComponent {
    val detailsModule = DetailsModule.Impl(dependencies = dependencies)

    return object : DetailsComponent, DetailsModule by detailsModule {}
}
