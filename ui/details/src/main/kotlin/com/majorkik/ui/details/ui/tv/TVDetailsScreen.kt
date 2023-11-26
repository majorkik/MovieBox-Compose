package com.majorkik.ui.details.ui.tv

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.majorkik.core.ui.theme.MBTheme
import com.majorkik.core.ui.theme.ThemePreview
import com.majorkik.core.ui.viewmodel.viewModel
import com.majorkik.tmdb.api.model.image.original
import com.majorkik.tmdb.api.model.tvDetailsPreview
import com.majorkik.ui.details.DetailsFeature.component
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun TVDetailsScreen(tvId: Int) {
    val viewModel: TVDetailsViewModel = viewModel { component().createTVDetailsViewModel(tvId = tvId) }
    val state by viewModel.state.collectAsState()

    TVDetailsScreen(state = state)
}

@Composable
private fun TVDetailsScreen(state: TVDetailsState) {
    when (state) {
        TVDetailsState.Loading -> LoadingStateContent()
        is TVDetailsState.Content -> TVDetailsContent(state = state)
        TVDetailsState.Error -> ErrorStateContent()
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun TVDetailsContent(state: TVDetailsState.Content) {
    val pagerState = rememberPagerState { state.details.posters.size }

    Column {
        HorizontalPager(state = pagerState) { page ->
            AsyncImage(
                model = state.details.backdrops.getOrNull(page)?.original.orEmpty(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(400.dp)
                    .fillMaxWidth()
            )
        }
    }
}

@Composable
private fun LoadingStateContent() {
    // TODO
}

@Composable
private fun ErrorStateContent() {
    // TODO
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
