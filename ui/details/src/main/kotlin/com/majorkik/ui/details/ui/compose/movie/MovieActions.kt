package com.majorkik.ui.details.ui.compose.movie

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.majorkik.core.localization.StringResource
import com.majorkik.core.ui.CoreDrawable
import com.majorkik.core.ui.theme.MBTheme
import com.majorkik.core.ui.theme.ThemePreview

@Composable
internal fun MovieActions(
    onWillWatchClick: () -> Unit,
    onBookmarksClick: () -> Unit,
    onFavoriteClick: () -> Unit,
) {
    // Block with buttons
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        // Button to add a movie to the "will watch" list
        Button(
            onClick = onWillWatchClick,
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .height(48.dp)
                .fillMaxWidth()
                .weight(1f),
            colors = ButtonDefaults.textButtonColors(
                containerColor = MBTheme.colors.background.accent, contentColor = MBTheme.colors.text.primaryOnDark
            )
        ) {
            Text(
                text = stringResource(StringResource.details_will_watch),
                maxLines = 1,
                style = MBTheme.typography.header.titleSmall,
            )
        }

        // Button to add a movie to bookmarks
        Surface(
            onClick = onBookmarksClick,
            modifier = Modifier.size(48.dp),
            shape = RoundedCornerShape(12.dp),
            color = MBTheme.colors.background.elevation1
        ) {
            Icon(
                painter = painterResource(id = CoreDrawable.ic_options_black_24),
                contentDescription = null,
                tint = MBTheme.colors.foreground.infoAccent,
                modifier = Modifier
                    .size(48.dp)
                    .padding(12.dp)
            )
        }

        // Button to add a movie to favorites
        Surface(
            onClick = onFavoriteClick,
            modifier = Modifier.size(48.dp),
            shape = RoundedCornerShape(12.dp),
            color = MBTheme.colors.background.elevation1
        ) {
            Icon(
                painter = painterResource(id = CoreDrawable.ic_round_favorite_black_24),
                contentDescription = null,
                tint = MBTheme.colors.foreground.infoAccent,
                modifier = Modifier
                    .size(48.dp)
                    .padding(16.dp)
            )
        }
    }
}

@ThemePreview
@Composable
private fun MovieActionsPreview() {
    MBTheme {
        MovieActions(onWillWatchClick = {}, onBookmarksClick = { }, onFavoriteClick = {})
    }
}
