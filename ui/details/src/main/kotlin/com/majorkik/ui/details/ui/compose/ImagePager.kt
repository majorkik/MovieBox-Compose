package com.majorkik.ui.details.ui.compose

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.majorkik.core.ui.theme.MBTheme
import com.majorkik.core.ui.theme.ThemePreview
import com.majorkik.tmdb.api.model.image.Backdrop
import com.majorkik.tmdb.api.model.image.original
import com.majorkik.tmdb.api.model.movieDetailsPreview
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toPersistentList

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun ImagePager(backdrops: ImmutableList<Backdrop>) {
    val pagerState = rememberPagerState { backdrops.size }

    HorizontalPager(state = pagerState) { page ->
        AsyncImage(
            model = backdrops.getOrNull(page)?.original.orEmpty(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(400.dp)
                .fillMaxWidth()
        )
    }
}

@ThemePreview
@Composable
private fun ImagePagerPreview() {
    MBTheme {
        val details = movieDetailsPreview()

        ImagePager(backdrops = details.backdrops.toPersistentList())
    }
}
