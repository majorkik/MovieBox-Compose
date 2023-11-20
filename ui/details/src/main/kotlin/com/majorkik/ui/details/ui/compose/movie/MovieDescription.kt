package com.majorkik.ui.details.ui.compose.movie

import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.majorkik.core.localization.StringResource
import com.majorkik.core.ui.theme.MBTheme
import com.majorkik.core.ui.theme.ThemePreview
import io.dokar.expandabletext.ExpandableText

@Composable
internal fun MovieDescription(overview: String?) {
    var overviewExpanded by remember { mutableStateOf(false) }

    if (overview != null) {
        ExpandableText(
            text = overview,
            modifier = Modifier
                .animateContentSize()
                .clickable { overviewExpanded = !overviewExpanded }
                .padding(horizontal = 16.dp),
            style = MBTheme.typography.body.textMedium,
            color = MBTheme.colors.text.primary,
            maxLines = 3,
            expanded = overviewExpanded,
            toggleContent = {
                @StringRes val textRes = if (overviewExpanded) {
                    StringResource.details_show_less
                } else {
                    StringResource.details_show_more
                }

                Text(
                    text = " " + stringResource(textRes),
                    style = MBTheme.typography.body.textMedium,
                    color = MBTheme.colors.text.accent
                )
            }
        )
    }
}

@ThemePreview
@Composable
private fun MovieDescriptionPreview() {
    MBTheme {
        MovieDescription(
            overview = "A superhero film series based on the Marvel Comics superhero team Guardians of the Galaxy," +
                    " and part of the Marvel Cinematic Universe (MCU) series. A superhero film series based on the" +
                    " Marvel Comics superhero team Guardians of the Galaxy, and part of the Marvel Cinematic" +
                    " Universe (MCU) series. "
        )
    }
}
