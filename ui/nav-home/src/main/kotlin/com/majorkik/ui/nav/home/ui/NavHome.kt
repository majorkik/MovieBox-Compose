package com.majorkik.ui.nav.home.ui

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.majorkik.core.localization.StringResource
import com.majorkik.core.ui.CoreDrawable
import com.majorkik.core.ui.components.InfiniteListHandler
import com.majorkik.core.ui.extension.clickableWithSimpleRipple
import com.majorkik.core.ui.extension.showToast
import com.majorkik.core.ui.theme.MBTheme
import com.majorkik.tmdb.api.model.Genre
import com.majorkik.ui.nav.home.component.AppBar
import com.majorkik.ui.nav.home.component.GenresSwitch
import com.majorkik.ui.nav.home.component.HorizontalMovieCard
import com.majorkik.ui.nav.home.component.RoundedButton
import com.majorkik.ui.nav.home.component.TitleText
import com.majorkik.ui.nav.home.component.VerticalMovieCard
import com.ramcosta.composedestinations.annotation.Destination
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@Destination
@Composable
fun NavHomeScreen(
    navigator: NavHomeNavigator,
) {
    val context = LocalContext.current
    val viewModel: NavHomeViewModelViewModel = getViewModel()
    val state by viewModel.container.stateFlow.collectAsState()

    NavHomeContent(
        state = state,
        actionToggleGenresMode = viewModel::toggleGenresMode,
        actionFetchPopularMovies = viewModel::fetchPopularMovies,
        actionFetchPopularTVs = viewModel::fetchPopularTVs,
        actionFetchTrendingMovies = viewModel::fetchTrendingMovies,
        actionFetchTrendingTVs = viewModel::fetchTrendingTVs,
        openMovieDetails = navigator::openMovieDetails,
        openGenresList = { /* no-op */ }
    )

    LaunchedEffect(Unit) {
        launch {
            viewModel.container.sideEffectFlow.collectLatest { sideEffect ->
                handleSideEffect(sideEffect = sideEffect, context = context)
            }
        }
    }
}

@Suppress("Detekt.LongMethod")
@Composable
internal fun NavHomeContent(
    state: NavHomeViewModelViewState,
    actionToggleGenresMode: () -> Unit,
    actionFetchPopularTVs: () -> Unit,
    actionFetchPopularMovies: () -> Unit,
    actionFetchTrendingTVs: () -> Unit,
    actionFetchTrendingMovies: () -> Unit,
    openMovieDetails: (Int) -> Unit,
    openGenresList: (Genre) -> Unit,
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .statusBarsPadding()
            .verticalScroll(state = scrollState)
            .fillMaxSize()
    ) {
        AppBar(onLoginClick = {}, onOpenSettings = {})

        // Description above search
        Text(
            stringResource(id = StringResource.nav_home_screen_search_desc),
            style = MBTheme.typography.body.medium,
            color = MBTheme.colors.text.primary,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        // Search bar with filter button
        Box(
            modifier = Modifier
                .padding(vertical = 16.dp)
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .height(48.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(MBTheme.colors.background.elevation1)
                .clickableWithSimpleRipple { }
        ) {
            // Temporary stub for search without the ability to enter text
            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .align(Alignment.Center),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = CoreDrawable.ic_search_black_24),
                    contentDescription = null,
                    tint = MBTheme.colors.foreground.infoAccent,
                )

                Text(
                    stringResource(StringResource.common_search),
                    style = MBTheme.typography.body.medium,
                    color = MBTheme.colors.text.primary
                )
            }

            // Filter button
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(MBTheme.colors.background.accent)
                    .clickableWithSimpleRipple { }
                    .align(Alignment.CenterEnd)
            ) {
                Icon(
                    painter = painterResource(id = CoreDrawable.ic_tune_white_24),
                    contentDescription = null,
                    tint = MBTheme.colors.foreground.onDark,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }

        // Vertical list of genres, movies and series lists
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            // List of genres with the ability to switch between movie / series genres
            // due to the fact that they may differ
            GenresBlock(
                genres = state.genres,
                isMovieGenresSelected = state.isMovieGenresSelected,
                onToggle = actionToggleGenresMode,
                onGenreClick = openGenresList
            )

            // Endless list with trending tv shows
            InfinityCollection(
                title = StringResource.common_trending_tv_shows,
                items = state.trendingTVsState.tvs,
                onLoadMore = actionFetchTrendingTVs
            ) { item ->
                VerticalMovieCard(
                    posterPath = item.posterPath,
                    title = item.title,
                    voteAverage = item.voteAverage,
                    releaseDate = item.releaseDate,
                    onClick = {}
                )
            }

            // Endless list with popular tv shows
            InfinityCollection(
                title = StringResource.common_popular_tvs,
                items = state.popularTVsState.tvs,
                onLoadMore = actionFetchPopularTVs
            ) { item ->
                HorizontalMovieCard(
                    backdropPath = item.backdropPath,
                    title = item.title,
                    voteAverage = item.voteAverage,
                    releaseDate = item.releaseDate,
                    onClick = {}
                )
            }

            // Endless list with trending movies
            InfinityCollection(
                title = StringResource.common_trending_movies,
                items = state.trendingMoviesState.movies,
                onLoadMore = actionFetchTrendingMovies
            ) { item ->
                VerticalMovieCard(
                    posterPath = item.posterPath,
                    title = item.title,
                    voteAverage = item.voteAverage,
                    releaseDate = item.releaseDate,
                    onClick = { openMovieDetails(item.id) }
                )
            }

            // Endless list with popular movies
            InfinityCollection(
                title = StringResource.common_popular_movies,
                items = state.popularMoviesState.movies,
                onLoadMore = actionFetchPopularMovies
            ) { item ->
                HorizontalMovieCard(
                    backdropPath = item.backdropPath,
                    title = item.title,
                    voteAverage = item.voteAverage,
                    releaseDate = item.releaseDate,
                    onClick = { openMovieDetails(item.id) }
                )
            }
        }
    }
}

@Composable
fun GenresBlock(
    genres: List<Genre>,
    isMovieGenresSelected: Boolean,
    onToggle: () -> Unit,
    onGenreClick: (Genre) -> Unit,
) {
    Column {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, end = 8.dp)
        ) {
            Text(
                text = stringResource(id = StringResource.common_genres),
                style = MBTheme.typography.header.h3,
                color = MBTheme.colors.text.primary
            )

            GenresSwitchBox(
                isMovieGenresSelected = isMovieGenresSelected,
                onToggleSwitch = onToggle
            )
        }

        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(genres) { genre ->
                RoundedButton(text = genre.name) { onGenreClick(genre) }
            }
        }
    }
}

@Composable
internal fun GenresSwitchBox(isMovieGenresSelected: Boolean, onToggleSwitch: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(space = 8.dp),
        modifier = Modifier
            .clip(CircleShape)
            .clickableWithSimpleRipple(onToggleSwitch)
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clipToBounds()
    ) {
        Text(
            text = stringResource(id = StringResource.common_movie),
            style = MBTheme.typography.header.titleMedium,
            color = if (isMovieGenresSelected) {
                MBTheme.colors.text.primary
            } else {
                MBTheme.colors.text.primaryInactive
            }
        )

        GenresSwitch(checked = isMovieGenresSelected.not())

        Text(
            text = stringResource(id = StringResource.common_tv),
            style = MBTheme.typography.header.titleMedium,
            color = if (isMovieGenresSelected.not()) {
                MBTheme.colors.text.primary
            } else {
                MBTheme.colors.text.primaryInactive
            }
        )
    }
}

@Composable
internal fun <T> InfinityCollection(
    @StringRes title: Int,
    items: List<T>,
    onLoadMore: () -> Unit,
    content: @Composable LazyItemScope.(T) -> Unit,
) {
    val listState = rememberLazyListState()

    Column {
        TitleText(text = title)

        LazyRow(
            state = listState,
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(items = items, itemContent = content)
        }

        InfiniteListHandler(listState = listState, buffer = 5, onLoadMore = onLoadMore)
    }
}

private fun handleSideEffect(sideEffect: NavHomeViewModelSideEffect, context: Context) {
    when (sideEffect) {
        is NavHomeViewModelSideEffect.ShowErrorToast -> {
            if (sideEffect.message == null) {
                context.showToast(StringResource.error_something_went_wrong)
            } else {
                context.showToast(message = sideEffect.message)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    MBTheme(isDark = true) {
        Surface(
            color = MBTheme.colors.background.base, modifier = Modifier
                .fillMaxWidth()
        ) {
            GenresBlock(
                genres = listOf(
                    Genre(id = 1, name = "Action"),
                    Genre(id = 2, name = "Adventure"),
                    Genre(id = 3, name = "Science Fiction")
                ),
                isMovieGenresSelected = true,
                onToggle = {},
                onGenreClick = {}
            )
        }
    }
}
