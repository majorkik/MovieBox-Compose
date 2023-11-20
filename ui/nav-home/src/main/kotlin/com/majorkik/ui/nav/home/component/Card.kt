package com.majorkik.ui.nav.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.majorkik.common.percentOf
import com.majorkik.core.ui.components.getPopcornPlaceholderResId
import com.majorkik.core.ui.extension.clickableWithSimpleRipple
import com.majorkik.core.ui.theme.MBTheme
import com.majorkik.core.ui.theme.ThemePreview
import com.majorkik.tmdb.api.model.image.Backdrop
import com.majorkik.tmdb.api.model.image.Poster
import com.soywiz.klock.Date
import com.soywiz.klock.DateTime

@Composable
internal fun HorizontalMovieCard(
    backdrop: Backdrop?,
    title: String,
    voteAverage: Double,
    releaseDate: Date?,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .size(width = 300.dp, height = 150.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(MBTheme.colors.background.elevation1)
            .clickableWithSimpleRipple(onClick),
        contentAlignment = Alignment.BottomStart
    ) {
        // Image
        Image(
            painter = rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current)
                    .data(data = backdrop?.build(size = Backdrop.Size.Width1280))
                    .apply(block = {
                        crossfade(true)
                        placeholder(getPopcornPlaceholderResId())
                        error(getPopcornPlaceholderResId())
                        fallback(getPopcornPlaceholderResId())
                    })
                    .build()
            ),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Rating as a percentage
        Text(
            "${voteAverage.percentOf(from = 10)}%",
            style = MBTheme.typography.header.titleSmall,
            color = MBTheme.colors.text.primaryOnDark,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(8.dp)
                .clip(CircleShape)
                .background(MBTheme.colors.background.neutral1)
                .padding(vertical = 4.dp, horizontal = 8.dp)
        )

        // Title and year of release
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                title,
                style = MBTheme.typography.header.h4,
                color = MBTheme.colors.text.primaryOnDark,
                maxLines = 1
            )

            if (releaseDate != null) {
                Text(
                    releaseDate.year.toString(),
                    style = MBTheme.typography.header.titleMedium,
                    color = MBTheme.colors.text.primaryOnDark
                )
            }
        }
    }
}

@Composable
internal fun VerticalMovieCard(
    poster: Poster?,
    title: String,
    voteAverage: Double,
    releaseDate: Date?,
    onClick: () -> Unit,
) {
    Column(modifier = Modifier.width(200.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(310.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(MBTheme.colors.background.elevation1)
                .clickableWithSimpleRipple(onClick),
        ) {
            Image(
                painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current)
                        .data(data = poster?.build(size = Poster.Size.Width500))
                        .apply(block = {
                            crossfade(true)
                            placeholder(getPopcornPlaceholderResId())
                            error(getPopcornPlaceholderResId())
                            fallback(getPopcornPlaceholderResId())
                        })
                        .build()
                ),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            PercentVoteAverageText(
                voteAverage = voteAverage,
                modifier = Modifier.align(Alignment.TopEnd)
            )

            if (releaseDate != null) {
                Text(
                    text = releaseDate.year.toString(),
                    style = MBTheme.typography.header.titleSmall,
                    color = MBTheme.colors.text.primaryOnDark,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(8.dp)
                        .clip(CircleShape)
                        .background(MBTheme.colors.background.info)
                        .padding(vertical = 4.dp, horizontal = 8.dp)
                )
            }
        }

        Text(
            text = title,
            style = MBTheme.typography.header.h4,
            color = MBTheme.colors.text.primary,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@ThemePreview
@Composable
fun HorizontalMovieCardPreview() {
    MBTheme {
        HorizontalMovieCard(
            backdrop = null,
            title = "Dune",
            voteAverage = 9.23,
            releaseDate = DateTime.now().date
        ) {}
    }
}

@ThemePreview
@Composable
fun VerticalMovieCardPreview() {
    MBTheme {
        VerticalMovieCard(
            poster = null,
            title = "Dune",
            voteAverage = 9.23,
            releaseDate = DateTime.now().date
        ) {}
    }
}
