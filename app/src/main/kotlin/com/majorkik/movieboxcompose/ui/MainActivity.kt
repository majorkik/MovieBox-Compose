package com.majorkik.movieboxcompose.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.majorkik.core.ui.CoreStyle
import com.majorkik.core.ui.theme.MBTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private val viewModel: MainActivityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(CoreStyle.Theme_MovieBoxCompose)
        super.onCreate(savedInstanceState)

        // This app draws behind the system bars, so we want to handle fitting system windows
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            val shouldUseDarkTheme =
                viewModel.shouldUseDarkTheme.collectAsState(initial = isSystemInDarkTheme())
            val systemUiController = rememberSystemUiController()

            MBTheme(isDark = shouldUseDarkTheme.value) {

                SideEffect {
                    systemUiController.setSystemBarsColor(
                        Color.Transparent,
                        darkIcons = shouldUseDarkTheme.value.not()
                    )
                }

                MainContainer()
            }
        }
    }
}
