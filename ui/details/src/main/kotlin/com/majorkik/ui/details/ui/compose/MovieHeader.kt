package com.majorkik.ui.details.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import arrow.core.getOrElse
import com.majorkik.common.AppDateFormat
import com.majorkik.core.ui.extension.clickableWithSimpleRipple
import com.majorkik.core.ui.theme.MBTheme
import com.majorkik.tmdb.api.model.MovieDetails
import com.majorkik.tmdb.api.model.movieDetailsPreview
import com.soywiz.klock.Date

@Composable
internal fun MovieHeader(details: MovieDetails) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Movie title
        Text(
            text = details.title,
            color = MBTheme.colors.text.primary,
            style = MBTheme.typography.header.h2,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
        )

        // Release date + status
        ReleaseDate(
            releaseDate = details.releaseDate,
            releaseStatus = details.status,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        // Genres
        Text(
            text = details.genres.joinToString { genre -> genre.name.capitalize(Locale.current) },
            color = MBTheme.colors.text.primary,
            style = MBTheme.typography.body.medium,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .clip(CircleShape)
                .clickableWithSimpleRipple {
                    /* no-op */
                }
                .padding(horizontal = 16.dp)
                .padding(vertical = 2.dp)
        )
    }
}

@Composable
private fun ReleaseDate(releaseDate: Date?, releaseStatus: String?, modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.padding(horizontal = 16.dp)
    ) {
        Text(
            text = AppDateFormat.parseReadableDate(releaseDate).getOrElse { "" },
            color = MBTheme.colors.text.primary,
            style = MBTheme.typography.body.medium
        )

        if (releaseStatus != null) {
            Text(
                text = releaseStatus,
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(MBTheme.colors.background.opposite)
                    .padding(horizontal = 8.dp, vertical = 4.dp),
                color = MBTheme.colors.text.primaryOnOpposite,
                style = MBTheme.typography.body.medium
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MovieActionsPreview() {
    MBTheme {
        MovieHeader(details = movieDetailsPreview())
    }
}
