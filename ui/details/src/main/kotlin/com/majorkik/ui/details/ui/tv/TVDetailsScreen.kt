package com.majorkik.ui.details.ui.tv

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.majorkik.core.localization.StringResource
import com.majorkik.core.ui.theme.MBTheme
import com.majorkik.tmdb.api.model.TVDetails
import com.majorkik.tmdb.api.model.tvDetailsPreview
import com.majorkik.ui.details.ui.compose.ImagePager
import com.ramcosta.composedestinations.annotation.Destination
import org.koin.androidx.compose.getViewModel

@Suppress("unused")
@Destination(navArgsDelegate = TVDetailsNavArgs::class)
@Composable
fun TVDetailsScreen() {
    val viewModel: TVDetailsViewModel = getViewModel()
    val state by viewModel.container.stateFlow.collectAsState()

    TVDetailsScreen(state = state)
}

/**
 * Container for handling screen states
 */
@Composable
private fun TVDetailsScreen(state: TVDetailsViewState) {
    when (state.screen) {
        is State.TVDetailsState -> TVDetailsContent(state.screen.data)
        is State.LoadingState -> LoadingStateContent()
        is State.ErrorState -> ErrorStateContent()
    }
}

/**
 * Screen status for displaying basic tv information
 */
@Composable
private fun TVDetailsContent(details: TVDetails) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(state = rememberScrollState())
    ) {
        // TV image pager
        ImagePager(backdrops = details.backdrops)
    }
}

/**
 * Screen state when loading tv details
 */
@Composable
private fun LoadingStateContent() {
    /* no-op */
}

/**
 * Screen state when an error occurs while executing a network request
 */
@Composable
private fun ErrorStateContent() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(text = stringResource(StringResource.error_state))
    }
}

@Preview(showBackground = true)
@Composable
private fun TVDetailsContentPreview() {
    MBTheme(isDark = false) {
        TVDetailsScreen(
            state = TVDetailsViewState(
                screen = State.TVDetailsState(
                    data = tvDetailsPreview()
                )
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TVDetailsErrorStatePreview() {
    MBTheme(isDark = false) {
        TVDetailsScreen(state = TVDetailsViewState(screen = State.ErrorState))
    }
}

@Preview(showBackground = true)
@Composable
private fun TVDetailsLoadingStatePreview() {
    MBTheme(isDark = false) {
        TVDetailsScreen(state = TVDetailsViewState(screen = State.LoadingState))
    }
}
