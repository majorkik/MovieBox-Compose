package com.majorkik.movieboxcompose.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.majorkik.core.ui.theme.MovieBoxTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private val viewModel: MainActivityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(com.majorkik.core.ui.R.style.Theme_MovieBoxCompose)
        super.onCreate(savedInstanceState)
//
//        val wrong = emptyList<Int>().get(2)
//
//        println(wrong)

        // This app draws behind the system bars, so we want to handle fitting system windows
        WindowCompat.setDecorFitsSystemWindows(window, true)

        setContent {
            val shouldUseDarkTheme = viewModel.shouldUseDarkTheme.collectAsState(initial = isSystemInDarkTheme())
            val systemUiController = rememberSystemUiController()

            MovieBoxTheme(isDark = shouldUseDarkTheme.value) {
                val color = MovieBoxTheme.colors.background

                SideEffect { systemUiController.setStatusBarColor(color = color) }

                ProvideWindowInsets {
                    MainContainer()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MainContainer()
}
