package com.majorkik.ui.details.ui.compose.movie

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.majorkik.core.ui.theme.MBTheme

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

@Preview(showBackground = true)
@Composable
private fun MovieActionsPreview() {
    MBTheme {
        MovieDetailsBlock(title = "Release date", description = "Example description")
    }
}
