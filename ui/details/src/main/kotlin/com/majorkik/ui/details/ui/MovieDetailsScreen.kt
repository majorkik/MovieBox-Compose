package com.majorkik.ui.details.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.majorkik.core.localization.StringResource
import com.majorkik.core.ui.theme.MBTheme
import com.majorkik.tmdb.api.model.MovieDetails
import com.majorkik.tmdb.api.model.movieDetailsPreview
import com.majorkik.ui.details.ui.compose.MovieCreditsBlock
import com.majorkik.ui.details.ui.compose.MovieDescription
import com.majorkik.ui.details.ui.compose.MovieDetailsBlock
import com.majorkik.ui.details.ui.compose.MovieHeader
import com.majorkik.ui.details.ui.compose.MovieImagePager
import com.majorkik.ui.details.ui.compose.MovieTagline
import com.ramcosta.composedestinations.annotation.Destination
import org.koin.androidx.compose.getViewModel

@Destination(navArgsDelegate = MovieDetailsNavArgs::class)
@Composable
fun MovieDetailsScreen() {
    val viewModel: MovieDetailsViewModel = getViewModel()
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
        MovieImagePager(backdrops = details.backdrops)
        MovieHeader(details = details)
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
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(space = 8.dp)
        ) {
            // Film's budget-to-revenue ratio
            MovieDetailsBlock(
                title = stringResource(
                    StringResource.details_revenue_slash_budget_value,
                    details.revenue,
                    details.budget
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

@Preview(showBackground = true)
@Composable
private fun MovieDetailsPreview() {
    MBTheme(isDark = false) {
        MovieDetailsScreen(
            state = MovieDetailsViewState(
                screen = State.MovieDetailsState(
                    data = movieDetailsPreview()
                )
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MovieDetailsErrorStatePreview() {
    MBTheme(isDark = false) {
        MovieDetailsScreen(
            state = MovieDetailsViewState(
                screen = State.ErrorState
            )
        )
    }
}
