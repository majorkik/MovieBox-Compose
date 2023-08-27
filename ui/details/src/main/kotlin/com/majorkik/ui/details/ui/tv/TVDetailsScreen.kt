package com.majorkik.ui.details.ui.tv

import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination
import org.koin.androidx.compose.getViewModel

@Suppress("unused")
@Destination(navArgsDelegate = TVDetailsNavArgs::class)
@Composable
fun TVDetailsScreen() {
    val viewModel: TVDetailsViewModel = getViewModel()
}
