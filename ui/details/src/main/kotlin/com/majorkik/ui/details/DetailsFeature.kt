package com.majorkik.ui.details

import com.majorkik.ui.details.ui.di.DetailsComponent
import com.majorkik.ui.details.ui.di.DetailsDependencies

object DetailsFeature {

    lateinit var detailsDependencies: DetailsDependencies

    private var detailsComponentHolder: DetailsComponent? = null

    fun getApi(): DetailsApi = component().api

    internal fun component(): DetailsComponent = detailsComponentHolder ?: run {
        detailsComponentHolder = DetailsComponent(dependencies = detailsDependencies)

        requireNotNull(detailsComponentHolder)
    }

    internal fun reset() {
        detailsComponentHolder = null
    }
}
