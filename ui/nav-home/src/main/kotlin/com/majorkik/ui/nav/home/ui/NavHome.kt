package com.majorkik.ui.nav.home.ui

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.majorkik.core.ui.components.InfiniteListHandler
import com.majorkik.core.ui.extension.clickableWithSimpleRipple
import com.majorkik.core.ui.extension.showToast
import com.majorkik.core.ui.theme.MovieBoxTheme
import com.majorkik.tmdb.api.model.Genre
import com.majorkik.tmdb.api.model.Movie
import com.majorkik.tmdb.api.model.TV
import com.majorkik.ui.nav.home.component.GenresSwitch
import com.majorkik.ui.nav.home.component.HorizontalMovieCard
import com.majorkik.ui.nav.home.component.RoundedButton
import com.majorkik.ui.nav.home.component.TitleText
import com.majorkik.ui.nav.home.component.Toolbar
import com.majorkik.ui.nav.home.component.VerticalMovieCard
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel
import com.majorkik.core.ui.R as CoreRes

@Composable
fun NavHomeScreen() {
    NavHomeContent(viewModel = getViewModel())
}

@Composable
internal fun NavHomeContent(viewModel: NavHomeViewModelViewModel) {
    val context = LocalContext.current
    val state = viewModel.container.stateFlow.collectAsState()
    val scrollState = rememberScrollState()

    LaunchedEffect(viewModel) {
        launch {
            viewModel.container.sideEffectFlow.collectLatest { sideEffect ->
                handleSideEffect(sideEffect = sideEffect, context = context)
            }
        }
    }

    Column(
        modifier = Modifier
            .verticalScroll(state = scrollState)
            .fillMaxSize()
    ) {
        Toolbar(onLoginClick = {}, onOpenSettings = {})

        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            GenresBlock(
                genres = state.value.genres,
                isMovieGenresSelected = state.value.isMovieGenresSelected,
                onToggle = viewModel::toggleGenresMode,
                onGenreClick = {
                    // Navigate to search with selected genre
                })

            InfinityCollection(
                title = CoreRes.string.trending_tv_shows,
                items = state.value.trendingTVsState.tvs,
                onLoadMore = viewModel::fetchTrendingTVs
            ) { item ->
                VerticalMovieCard(
                    posterPath = item.posterPath,
                    title = item.title,
                    voteAverage = item.voteAverage,
                    releaseDate = item.releaseDate,
                    onClick = {}
                )
            }

            InfinityCollection(
                title = CoreRes.string.popular_tvs,
                items = state.value.popularTVsState.tvs,
                onLoadMore = viewModel::fetchPopularTVs
            ) { item ->
                HorizontalMovieCard(
                    backdropPath = item.backdropPath,
                    title = item.title,
                    voteAverage = item.voteAverage,
                    releaseDate = item.releaseDate,
                    onClick = {}
                )
            }

            InfinityCollection(
                title = CoreRes.string.trending_movies,
                items = state.value.trendingMoviesState.movies,
                onLoadMore = viewModel::fetchTrendingMovies
            ) { item ->
                VerticalMovieCard(
                    posterPath = item.posterPath,
                    title = item.title,
                    voteAverage = item.voteAverage,
                    releaseDate = item.releaseDate,
                    onClick = {}
                )
            }

            InfinityCollection(
                title = CoreRes.string.popular_movies,
                items = state.value.popularMoviesState.movies,
                onLoadMore = viewModel::fetchPopularMovies
            ) { item ->
                HorizontalMovieCard(
                    backdropPath = item.backdropPath,
                    title = item.title,
                    voteAverage = item.voteAverage,
                    releaseDate = item.releaseDate,
                    onClick = {}
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
    onGenreClick: (Int) -> Unit
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
                text = stringResource(id = CoreRes.string.genres),
                style = MovieBoxTheme.typography.h3,
                color = MovieBoxTheme.colors.backgroundReverse
            )

            GenresSwitchBox(isMovieGenresSelected = isMovieGenresSelected, onToggleSwitch = onToggle)
        }

        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(genres) { genre ->
                RoundedButton(text = genre.name) { onGenreClick(genre.id) }
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
            text = stringResource(id = CoreRes.string.movie),
            style = MovieBoxTheme.typography.titleMedium,
            color = if (isMovieGenresSelected) {
                MovieBoxTheme.colors.backgroundReverse
            } else {
                MovieBoxTheme.colors.secondaryBackground
            }
        )

        GenresSwitch(checked = isMovieGenresSelected.not())

        Text(
            text = stringResource(id = CoreRes.string.tv),
            style = MovieBoxTheme.typography.titleMedium,
            color = if (isMovieGenresSelected.not()) {
                MovieBoxTheme.colors.backgroundReverse
            } else {
                MovieBoxTheme.colors.secondaryBackground
            }
        )
    }
}

@Composable
internal fun <T> InfinityCollection(
    @StringRes title: Int,
    items: List<T>,
    onLoadMore: () -> Unit,
    content: @Composable LazyItemScope.(T) -> Unit
) {
    val listState = rememberLazyListState()

    Column {
        TitleText(text = CoreRes.string.popular_tvs)

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
                context.showToast(CoreRes.string.error_something_went_wrong)
            } else {
                context.showToast(message = sideEffect.message)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    MovieBoxTheme(isDark = true) {
        Surface(
            color = MovieBoxTheme.colors.background, modifier = Modifier
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
