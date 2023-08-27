package com.majorkik.ui.nav.home.ui

interface NavHomeNavigator {
    fun navigateUp()
    fun openMovieDetails(movieId: Int)
    fun openTVDetails(tvId: Int)
}
