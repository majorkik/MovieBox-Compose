package com.majorkik.ui.nav.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.majorkik.core.localization.R
import com.majorkik.core.ui.CoreDrawable
import com.majorkik.core.ui.extension.clickableWithSimpleRipple
import com.majorkik.core.ui.theme.MBTheme
import com.majorkik.core.ui.theme.ThemePreview

@Composable
internal fun RoundedButton(text: String, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Text(
        text = text,
        textAlign = TextAlign.Center,
        modifier = modifier
            .clip(CircleShape)
            .background(MBTheme.colors.background.opposite)
            .padding(horizontal = 12.dp, vertical = 8.dp)
            .clickableWithSimpleRipple(onClick),
        style = MBTheme.typography.body.medium,
        color = MBTheme.colors.text.primaryOnOpposite
    )
}

@Composable
internal fun LoginButton(onClick: () -> Unit) {
    Surface(
        modifier = Modifier
            .clip(CircleShape)
            .clickableWithSimpleRipple(onClick),
        color = Color.Transparent
    ) {
        Row(modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = CoreDrawable.ic_round_arrow_right_black_24),
                contentDescription = null,
                tint = MBTheme.colors.foreground.positiveAccent
            )

            Text(
                stringResource(id = R.string.nav_home_screen_login_button),
                modifier = Modifier,
                style = MBTheme.typography.header.h4,
                color = MBTheme.colors.text.positiveAccent
            )
        }
    }
}

@ThemePreview
@Composable
fun RoundedButtonPreview() {
    MBTheme {
        RoundedButton(text = "Button") {
            /* no-op */
        }
    }
}

@ThemePreview
@Composable
fun LoginButtonPreview() {
    MBTheme {
        LoginButton {
            /* no-op */
        }
    }
}

