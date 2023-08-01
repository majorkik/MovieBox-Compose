package com.majorkik.ui.details.ui.compose

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.majorkik.core.ui.theme.MBTheme
import com.majorkik.tmdb.api.model.BackdropPath
import com.majorkik.tmdb.api.model.movieDetailsPreview
import com.majorkik.tmdb.api.model.original

@OptIn(ExperimentalPagerApi::class)
@Composable
internal fun MovieImagePager(backdrops: List<BackdropPath>) {
    HorizontalPager(count = backdrops.size) { page ->
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

@Preview(showBackground = true)
@Composable
private fun MovieActionsPreview() {
    MBTheme {
        val details = movieDetailsPreview()

        MovieImagePager(backdrops = details.backdrops)
    }
}
