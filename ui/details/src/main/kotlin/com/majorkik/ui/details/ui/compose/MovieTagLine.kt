package com.majorkik.ui.details.ui.compose

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.majorkik.core.ui.CoreDrawable
import com.majorkik.core.ui.theme.MBTheme
import com.majorkik.tmdb.api.model.movieDetailsPreview

@Composable
internal fun MovieTagline(tagline: String?) {
    val quoteLeadingId = "quote_leading_id"
    val quoteTrailingId = "quote_trailing_id"

    val inlineContentMap = mapOf(
        quoteLeadingId to buildQuoteInlineContent(CoreDrawable.ic_quote_leading_yellow_12),
        quoteTrailingId to buildQuoteInlineContent(CoreDrawable.ic_quote_trailing_yellow_12),
    )

    val annotatedString = buildAnnotatedString {
        appendInlineContent(id = quoteLeadingId)
        append(tagline.orEmpty())
        appendInlineContent(id = quoteTrailingId)
    }

    AnimatedVisibility(visible = tagline.isNullOrBlank().not()) {
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .heightIn(min = 40.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(MBTheme.colors.background.elevation1)
                .padding(horizontal = 8.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = annotatedString,
                style = MBTheme.typography.body.text,
                modifier = Modifier.align(Alignment.CenterVertically),
                inlineContent = inlineContentMap,
                color = MBTheme.colors.text.primary
            )
        }
    }
}

private fun buildQuoteInlineContent(@DrawableRes drawableRes: Int) = InlineTextContent(
    Placeholder(12.sp, 12.sp, PlaceholderVerticalAlign.TextTop)
) {
    Icon(
        painter = painterResource(id = drawableRes),
        modifier = Modifier.size(12.dp),
        contentDescription = "",
        tint = MBTheme.colors.text.primary
    )
}

@Preview(showBackground = true)
@Composable
private fun MovieActionsPreview() {
    MBTheme {
        MovieTagline(tagline = movieDetailsPreview().tagline)
    }
}
