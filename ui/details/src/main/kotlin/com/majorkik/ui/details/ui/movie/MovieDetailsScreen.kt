package com.majorkik.ui.details.ui.movie

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.majorkik.core.localization.StringResource
import com.majorkik.core.ui.theme.MBTheme
import com.majorkik.core.ui.theme.ThemePreview
import com.majorkik.core.ui.viewmodel.viewModel
import com.majorkik.tmdb.api.model.MovieDetails
import com.majorkik.tmdb.api.model.movieDetailsPreview
import com.majorkik.ui.details.DetailsFeature.component
import com.majorkik.ui.details.ui.compose.ImagePager
import com.majorkik.ui.details.ui.compose.movie.MovieActions
import com.majorkik.ui.details.ui.compose.movie.MovieCreditsBlock
import com.majorkik.ui.details.ui.compose.movie.MovieDescription
import com.majorkik.ui.details.ui.compose.movie.MovieDetailsBlock
import com.majorkik.ui.details.ui.compose.movie.MovieHeader
import com.majorkik.ui.details.ui.compose.movie.MovieTagline
import com.ramcosta.composedestinations.annotation.Destination
import org.koin.androidx.compose.getViewModel

@Destination
@Composable
fun MovieDetailsScreen(movieId: Int) {
    val viewModel: MovieDetailsViewModel = viewModel { component().createMovieDetailsViewModel(movieId = movieId) }
    val state by viewModel.container.stateFlow.collectAsState()

    MovieDetailsScreen(state)
}

/**
 * Container for handling screen states
 */
@Composable
internal fun MovieDetailsScreen(state: MovieDetailsViewState) {
    when (state.screen) {
        is State.MovieDetailsState -> MovieDetailsContent(details = state.screen.data)
        else -> ErrorStateContent()
    }
}

/**
 * Screen status for displaying basic movie information
 */
@Composable
private fun MovieDetailsContent(details: MovieDetails) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(state = rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Movie image pager
        ImagePager(backdrops = details.backdrops)
        // Header under image pager
        MovieHeader(details = details)
        // Movie action block
        MovieActions(onWillWatchClick = { }, onBookmarksClick = { }, onFavoriteClick = {})
        // Expandable movie description
        MovieDescription(overview = details.overview)
        // Tagline
        MovieTagline(tagline = details.tagline)
        // Cast list (max. 5 people) and "More" button
        MovieCreditsBlock(
            casts = details.casts,
            totalAmount = details.casts.count() + details.crews.count(),
            modifier = Modifier.padding(horizontal = 12.dp)
        )
        // Detailed information about the film, budget, box office and more
        Column(
            modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(space = 8.dp)
        ) {
            // Film's budget-to-revenue ratio
            MovieDetailsBlock(
                title = stringResource(
                    StringResource.details_revenue_slash_budget_value, details.revenue, details.budget
                ),
                description = stringResource(StringResource.details_revenue_slash_budget),
                modifier = Modifier.padding(horizontal = 16.dp)

            )

            // Original title
            MovieDetailsBlock(
                title = details.originalTitle,
                description = stringResource(StringResource.details_original_title),
                modifier = Modifier.padding(horizontal = 16.dp)

            )
        }
    }
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

@ThemePreview
@Composable
private fun MovieDetailsErrorStatePreview(
    @PreviewParameter(MovieDetailsStateProvider::class) state: State,
) {
    MBTheme(isDark = false) {
        MovieDetailsScreen(MovieDetailsViewState(state))
    }
}

private class MovieDetailsStateProvider : PreviewParameterProvider<State> {
    override val values = sequenceOf(
        State.MovieDetailsState(movieDetailsPreview()),
        State.ErrorState,
    )

}
