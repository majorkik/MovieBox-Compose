package com.majorkik.ui.details.ui.compose.movie

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.majorkik.core.ui.theme.MBTheme
import com.majorkik.core.ui.theme.ThemePreview

@Composable
internal fun MovieDetailsBlock(title: String, description: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            text = title,
            style = MBTheme.typography.body.textMedium,
            color = MBTheme.colors.text.primary
        )

        Text(
            text = description,
            style = MBTheme.typography.ui.captionMedium,
            color = MBTheme.colors.text.secondary
        )
    }
}

@ThemePreview
@Composable
private fun MovieActionsPreview() {
    MBTheme {
        MovieDetailsBlock(title = "Release date", description = "Example description")
    }
}
