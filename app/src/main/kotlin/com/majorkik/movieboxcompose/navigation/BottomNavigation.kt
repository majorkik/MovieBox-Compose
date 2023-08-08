package com.majorkik.movieboxcompose.navigation

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.majorkik.core.ui.CoreDrawable
import com.majorkik.core.ui.theme.MBTheme
import com.ramcosta.composedestinations.spec.NavGraphSpec

@Composable
fun BottomNavigation(navController: NavController) {
    val currentSelectedItem by navController.currentScreenAsState()

    NavigationBar(
        containerColor = MBTheme.colors.background.base,
        modifier = Modifier
            .navigationBarsPadding()
            .imePadding()
            .background(MBTheme.colors.background.base),
    ) {
        BottomNavigationItems.forEach { item ->
            val isSelected = currentSelectedItem == item.screen

            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    navController.navigate(item.screen.route) {
                        launchSingleTop = true
                        restoreState = true

                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                    }
                },
                icon = {
                    BottomNavigationIcon(item = item, isSelected = isSelected)
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = MBTheme.colors.background.elevation1
                )
            )
        }
    }
}

data class BottomNavigationItem(
    val screen: NavGraphSpec,
    @DrawableRes val iconResId: Int,
    @DrawableRes val selectedIconResId: Int? = null,
)

val BottomNavigationItems = listOf(
    BottomNavigationItem(
        screen = NavGraphs.home,
        iconResId = CoreDrawable.ic_tv_black_24,
        selectedIconResId = CoreDrawable.ic_tv_black_24
    ),

    BottomNavigationItem(
        screen = NavGraphs.search,
        iconResId = CoreDrawable.ic_search_black_24,
        selectedIconResId = CoreDrawable.ic_search_black_24
    ),

    BottomNavigationItem(
        screen = NavGraphs.profile,
        iconResId = CoreDrawable.ic_profile_black_24,
        selectedIconResId = CoreDrawable.ic_profile_black_24
    ),
)

@Composable
fun BottomNavigationIcon(item: BottomNavigationItem, isSelected: Boolean) {
    Icon(
        painter = painterResource(id = item.iconResId),
        contentDescription = null,
        tint = if (isSelected) {
            MBTheme.colors.foreground.accent
        } else {
            MBTheme.colors.foreground.infoAccent
        }
    )
}

/**
 * Adds an [NavController.OnDestinationChangedListener] to this [NavController] and updates the
 * returned [State] which is updated as the destination changes.
 */
@Stable
@Composable
private fun NavController.currentScreenAsState(): State<NavGraphSpec> {
    val selectedItem = remember { mutableStateOf(NavGraphs.home) }

    DisposableEffect(this) {
        val listener = NavController.OnDestinationChangedListener { _, destination, _ ->
            selectedItem.value = destination.navGraph()
        }

        addOnDestinationChangedListener(listener)

        onDispose {
            removeOnDestinationChangedListener(listener)
        }
    }

    return selectedItem
}
