package com.majorkik.ui.details.ui.tv

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.majorkik.core.ui.theme.MBTheme
import com.majorkik.core.ui.theme.ThemePreview
import com.majorkik.tmdb.api.model.tvDetailsPreview
import com.ramcosta.composedestinations.annotation.Destination
import org.koin.androidx.compose.getViewModel

@Destination(navArgsDelegate = TVDetailsNavArgs::class)
@Composable
fun TVDetailsScreen() {
    val viewModel: TVDetailsViewModel = getViewModel()
    val state by viewModel.state.collectAsState()

    TVDetailsScreen(state = state)
}

@Composable
private fun TVDetailsScreen(state: TVDetailsState) {
    when (state) {
        TVDetailsState.Loading -> LoadingStateContent()
        is TVDetailsState.Content -> TVDetailsScreen(state = state)
        TVDetailsState.Error -> ErrorStateContent()
    }
}

@Composable
private fun TVDetailsContent(state: TVDetailsState.Content) {

}

@Composable
private fun LoadingStateContent() {

}

@Composable
private fun ErrorStateContent() {

}

@ThemePreview
@Composable
private fun TVDetailsLoadingStatePreview(
    @PreviewParameter(TVDetailsStateProvider::class) state: TVDetailsState,
) {
    MBTheme {
        TVDetailsScreen(state = state)
    }
}

private class TVDetailsStateProvider : PreviewParameterProvider<TVDetailsState> {
    override val values = sequenceOf(
        TVDetailsState.Loading,
        TVDetailsState.Content(details = tvDetailsPreview()),
        TVDetailsState.Error,
    )
}
