package com.majorkik.movieboxcompose.navigation

import com.majorkik.ui.details.ui.destinations.MovieDetailsScreenDestination
import com.majorkik.ui.nav.home.ui.destinations.NavHomeScreenDestination
import com.majorkik.ui.nav.profile.ui.destinations.NavProfileScreenDestination
import com.majorkik.ui.nav.search.ui.destinations.NavSearchScreenDestination
import com.majorkik.ui.authorization.ui.destinations.AuthScreenDestination
import com.majorkik.ui.details.ui.destinations.TVDetailsScreenDestination
import com.ramcosta.composedestinations.dynamic.routedIn
import com.ramcosta.composedestinations.spec.DestinationSpec
import com.ramcosta.composedestinations.spec.NavGraphSpec

object NavGraphs {
    val home = object : NavGraphSpec {
        override val route = "search"
        override val startRoute = NavHomeScreenDestination routedIn this
        override val destinationsByRoute = listOf<DestinationSpec<*>>(
            NavHomeScreenDestination,
            MovieDetailsScreenDestination,
            TVDetailsScreenDestination,
        ).routedIn(this)
            .associateBy { it.route }
    }

    val search = object : NavGraphSpec {
        override val route = "watched"
        override val startRoute = NavSearchScreenDestination routedIn this
        override val destinationsByRoute = listOf<DestinationSpec<*>>(
            NavSearchScreenDestination
        ).routedIn(this)
            .associateBy { it.route }
    }

    val profile = object : NavGraphSpec {
        override val route = "following"

        override val startRoute = NavProfileScreenDestination routedIn this

        override val destinationsByRoute = listOf<DestinationSpec<*>>(
            NavProfileScreenDestination,
            AuthScreenDestination
        ).routedIn(this)
            .associateBy { it.route }
    }

    val root = object : NavGraphSpec {
        override val route = "root"
        override val startRoute = home
        override val destinationsByRoute = emptyMap<String, DestinationSpec<*>>()
        override val nestedNavGraphs = listOf(
            home,
            search,
            profile
        )
    }
}
