package com.majorkik.movieboxcompose.navigation

import androidx.navigation.NavController
import com.majorkik.ui.authorization.ui.AuthNavigator
import com.majorkik.ui.details.ui.destinations.MovieDetailsScreenDestination
import com.majorkik.ui.authorization.ui.destinations.AuthScreenDestination
import com.majorkik.ui.details.ui.destinations.TVDetailsScreenDestination
import com.majorkik.ui.nav.home.ui.NavHomeNavigator
import com.majorkik.ui.nav.profile.ui.NavProfileNavigator
import com.ramcosta.composedestinations.dynamic.within
import com.ramcosta.composedestinations.navigation.navigate
import com.ramcosta.composedestinations.spec.NavGraphSpec

class CommonNavigator(
    private val navGraph: NavGraphSpec,
    private val navController: NavController
) : NavHomeNavigator, NavProfileNavigator, AuthNavigator {
    override fun navigateUp() {
        navController.navigateUp()
    }

    override fun openMovieDetails(movieId: Int) {
        val direction = MovieDetailsScreenDestination(movieId = movieId) within navGraph
        navController.navigate(direction)
    }

    override fun openTVDetails(tvId: Int) {
        val direction = TVDetailsScreenDestination(tvId = tvId) within navGraph
         navController.navigate(direction = direction)
    }

    override fun openAuthorization() {
        navController.navigate(AuthScreenDestination within navGraph)
    }
}
