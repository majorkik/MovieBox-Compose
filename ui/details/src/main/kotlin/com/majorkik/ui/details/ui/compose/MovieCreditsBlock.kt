package com.majorkik.ui.details.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.majorkik.core.localization.StringResource
import com.majorkik.core.ui.components.getSmallProfilePlaceholder
import com.majorkik.core.ui.extension.clickableWithSimpleRipple
import com.majorkik.core.ui.theme.MBTheme
import com.majorkik.tmdb.api.model.MovieDetails
import com.majorkik.tmdb.api.model.ProfilePath
import com.majorkik.tmdb.api.model.movieDetailsPreview

@Composable
internal fun MovieCreditsBlock(
    casts: List<MovieDetails.Cast>,
    totalAmount: Int,
    modifier: Modifier = Modifier,
) {
    Row {
        Row(
            modifier = modifier
                .clip(CircleShape)
                .clickableWithSimpleRipple { /* no-op */ }
                .padding(4.dp),
            horizontalArrangement = Arrangement.spacedBy((-8).dp)
        ) {
            casts.take(n = 5).forEach { cast ->
                AsyncImage(
                    model = cast.profilePath?.build(size = ProfilePath.Size.Width45),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(32.dp)
                        .border(
                            width = 2.dp,
                            color = MBTheme.colors.background.base,
                            shape = CircleShape
                        )
                        .padding(2.dp)
                        .clip(CircleShape)
                        .background(MBTheme.colors.background.opposite),
                    placeholder = getSmallProfilePlaceholder(isLight = MBTheme.colors.isLight),
                    error = getSmallProfilePlaceholder(isLight = MBTheme.colors.isLight)
                )
            }

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .height(32.dp)
                    .border(
                        width = 2.dp,
                        color = MBTheme.colors.background.base,
                        shape = CircleShape
                    )
                    .padding(2.dp)
                    .clip(CircleShape)
                    .background(MBTheme.colors.background.opposite)
                    .padding(horizontal = 16.dp),
            ) {
                Text(
                    text = stringResource(StringResource.common_more),
                    style = MBTheme.typography.ui.captionMedium,
                    color = MBTheme.colors.text.primaryOnDark,
                    textAlign = TextAlign.Center
                )
            }
        }

        if (totalAmount > 5) {
            Text(
                text = stringResource(StringResource.details_casts_count_plus, totalAmount),
                modifier = Modifier.align(Alignment.CenterVertically),
                color = MBTheme.colors.text.primary,
                style = MBTheme.typography.ui.captionMedium,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MovieActionsPreview() {
    MBTheme {
        val details = movieDetailsPreview()

        MovieCreditsBlock(casts = details.casts, totalAmount = details.casts.count())
    }
}
