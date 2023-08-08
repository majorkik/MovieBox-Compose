package com.majorkik.movieboxcompose.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.majorkik.core.ui.theme.MBTheme
import com.majorkik.movieboxcompose.navigation.BottomNavigation
import com.majorkik.movieboxcompose.navigation.CommonNavigator
import com.majorkik.movieboxcompose.navigation.NavGraphs
import com.majorkik.movieboxcompose.navigation.defaultEnterTransition
import com.majorkik.movieboxcompose.navigation.defaultExitTransition
import com.majorkik.movieboxcompose.navigation.defaultPopEnterTransition
import com.majorkik.movieboxcompose.navigation.defaultPopExitTransition
import com.majorkik.movieboxcompose.navigation.navGraph
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.animations.defaults.RootNavGraphDefaultAnimations
import com.ramcosta.composedestinations.navigation.DependenciesContainerBuilder
import com.ramcosta.composedestinations.navigation.dependency
import com.ramcosta.composedestinations.animations.rememberAnimatedNavHostEngine as rememberAnimatedNavHostEngine1

@OptIn(
    ExperimentalAnimationApi::class,
    ExperimentalMaterialNavigationApi::class
)
@Composable
fun MainContainer() {
    val navController = rememberAnimatedNavController()

    Scaffold(
        containerColor = MBTheme.colors.background.base,
        bottomBar = { BottomNavigation(navController = navController) },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        DestinationsNavHost(
            engine = rememberAnimatedNavHostEngine1(
                rootDefaultAnimations = RootNavGraphDefaultAnimations(
                    enterTransition = { defaultEnterTransition(initialState, targetState) },
                    exitTransition = { defaultExitTransition(initialState, targetState) },
                    popEnterTransition = { defaultPopEnterTransition() },
                    popExitTransition = { defaultPopExitTransition() },
                )
            ),
            navController = navController,
            navGraph = NavGraphs.root,
            modifier = Modifier
                .padding(bottom = innerPadding.calculateBottomPadding()),
            dependenciesContainerBuilder = {
                dependency(currentNavigator())
            }
        )
    }
}

fun DependenciesContainerBuilder<*>.currentNavigator(): CommonNavigator {
    return CommonNavigator(
        navBackStackEntry.destination.navGraph(),
        navController
    )
}
