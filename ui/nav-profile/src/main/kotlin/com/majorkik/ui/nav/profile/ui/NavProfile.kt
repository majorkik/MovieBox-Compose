package com.majorkik.ui.nav.profile.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.majorkik.core.ui.CoreDrawable
import com.majorkik.core.ui.theme.MBTheme
import com.ramcosta.composedestinations.annotation.Destination
import org.koin.androidx.compose.getViewModel

@Destination
@Composable
fun NavProfileScreen(navigator: NavProfileNavigator) {
    val viewModel: NavProfileViewModel = getViewModel()

    NavProfileContent(
        enableDarkTheme = viewModel::actionSaveTheme,
        openAuthorization = navigator::openAuthorization
    )
}

@Composable
internal fun NavProfileContent(enableDarkTheme: (Boolean) -> Unit, openAuthorization: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MBTheme.colors.background.base)
            .statusBarsPadding()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.End
        ) {
            ThemeButton(enableDarkTheme)
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(onClick = openAuthorization) {
            Text(text = "Open authorization")
        }
    }
}

@Composable
internal fun ThemeButton(enableDarkTheme: (Boolean) -> Unit) {
    val isDark = MBTheme.colors.isLight.not()

    IconToggleButton(
        checked = isDark,
        onCheckedChange = enableDarkTheme
    ) {
        if (isDark) {
            Icon(
                painter = painterResource(id = CoreDrawable.ic_light_mode_black_24dp),
                contentDescription = null,
                tint = MBTheme.colors.foreground.neutralAccent
            )
        } else {
            Icon(
                painter = painterResource(id = CoreDrawable.ic_dark_mode_black_24),
                contentDescription = null,
                tint = MBTheme.colors.foreground.neutralAccent
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
internal fun HomeContentPreview() {
    MBTheme(isDark = true) {
        NavProfileContent(enableDarkTheme = {}, openAuthorization = {})
    }
}
