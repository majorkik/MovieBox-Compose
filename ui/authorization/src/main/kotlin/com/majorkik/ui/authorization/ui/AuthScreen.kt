package com.majorkik.ui.authorization.ui

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.majorkik.core.localization.StringResource
import com.majorkik.core.ui.CoreDrawable
import com.majorkik.core.ui.components.button.MBPasswordTextField
import com.majorkik.core.ui.components.button.MBTextField
import com.majorkik.core.ui.theme.MBTheme
import com.ramcosta.composedestinations.annotation.Destination
import org.koin.androidx.compose.getViewModel

@Destination
@Composable
fun AuthScreen(navigator: AuthNavigator) {
    val viewModel: AuthViewModel = getViewModel()
    val viewState by viewModel.container.stateFlow.collectAsState()

    AuthContent(
        state = viewState,
        onBackPress = navigator::navigateUp,
        updateLogin = viewModel::actionUpdateLogin,
        updatePassword = viewModel::actionUpdatePassword
    )
}

@Composable
private fun AuthContent(
    state: AuthViewState,
    onBackPress: () -> Unit,
    updateLogin: (String) -> Unit,
    updatePassword: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .background(MBTheme.colors.background.base)
    ) {
        Column {
            // Several cards with movie posters
            MovieCards(modifier = Modifier.weight(1f))

            // App name
            Text(
                text = stringResource(StringResource.app_title).uppercase(),
                style = MBTheme.typography.ui.logoLarge,
                color = MBTheme.colors.text.primary,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )

            Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.Center) {
                Spacer(modifier = Modifier.weight(1f))

                // Login field
                MBTextField(
                    value = state.login,
                    hint = stringResource(StringResource.auth_login_field_hint),
                    onValueChange = updateLogin,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // Password field
                    MBPasswordTextField(
                        value = state.password,
                        hint = stringResource(StringResource.auth_password_field_hint),
                        onValueChange = updatePassword,
                        modifier = Modifier.weight(1f),
                    )

                    // Sign-in button
                    Surface(
                        onClick = {},
                        shape = RoundedCornerShape(12.dp),
                        color = MBTheme.colors.background.accent,
                        modifier = Modifier.size(48.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(
                                painter = painterResource(id = CoreDrawable.ic_arrow_right_24),
                                tint = MBTheme.colors.foreground.onDark,
                                contentDescription = null,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }
                }

                // Sign up description
                SignUpDescription(
                    modifier = Modifier.padding(
                        horizontal = 16.dp,
                        vertical = 16.dp
                    )
                ) {
                    /* TODO */
                }

                // A button to log in as a guest
                Button(
                    onClick = { /*TODO*/ },
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .padding(horizontal = 16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MBTheme.colors.background.elevation1)
                ) {
                    Text(
                        text = stringResource(StringResource.auth_sign_in_guest),
                        style = MBTheme.typography.body.medium,
                        color = MBTheme.colors.text.secondary
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                // Description of the "Log in as a guest" button
                Text(
                    text = stringResource(StringResource.auth_sign_in_guest_description),
                    style = MBTheme.typography.body.captionSmall,
                    color = MBTheme.colors.text.secondary,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.weight(1f))

                Divider(color = MBTheme.colors.foreground.divider)

                // Bottom panel (clickable) to go to the TMDB information page
                FooterButton { /** TODO */ }
            }
        }

        // Back button
        IconButton(onClick = onBackPress, modifier = Modifier.align(Alignment.TopStart)) {
            Icon(
                painter = painterResource(id = CoreDrawable.ic_arrow_left_24),
                contentDescription = null,
                tint = MBTheme.colors.background.opposite
            )
        }
    }
}

@Composable
private fun MovieCards(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Image(
            painter = painterResource(id = CoreDrawable.image_sample_card_3_small),
            contentDescription = null,
            modifier = Modifier
                .offset(x = (-50).dp, y = 20.dp)
                .rotate(-15f)
                .shadow(elevation = 12.dp)
                .clip(RoundedCornerShape(12.dp))
                .size(width = 120.dp, height = 200.dp),
            contentScale = ContentScale.Crop
        )

        Image(
            painter = painterResource(id = CoreDrawable.image_sample_card_2_small),
            contentDescription = null,
            modifier = Modifier
                .shadow(elevation = 12.dp)
                .clip(RoundedCornerShape(12.dp))
                .size(width = 120.dp, height = 200.dp),
            contentScale = ContentScale.Crop
        )

        Image(
            painter = painterResource(id = CoreDrawable.image_sample_card_1_small),
            contentDescription = null,
            modifier = Modifier
                .offset(x = 50.dp, y = 20.dp)
                .rotate(15f)
                .shadow(elevation = 12.dp)
                .clip(RoundedCornerShape(12.dp))
                .size(width = 120.dp, height = 200.dp),
            contentScale = ContentScale.Crop
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FooterButton(onClick: () -> Unit) {
    val text = buildAnnotatedString {
        pushStyle(SpanStyle(color = MBTheme.colors.text.primary))

        append(stringResource(StringResource.auth_what_is))

        withStyle(style = SpanStyle(color = MBTheme.colors.text.tmdbBrand)) {
            append(" " + stringResource(StringResource.tmdb_full_title))
        }

        append("?")
    }

    Surface(onClick = onClick, color = Color.Transparent) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Text(
                text = text,
                modifier = Modifier,
                textAlign = TextAlign.Center,
                style = MBTheme.typography.body.textMedium
            )
        }
    }
}

@Composable
private fun SignUpDescription(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Divider(
            modifier = Modifier.weight(1f),
            color = MBTheme.colors.foreground.divider
        )

        val signUpText = buildAnnotatedString {
            pushStyle(SpanStyle(color = MBTheme.colors.text.primary))
            append(stringResource(StringResource.auth_sign_up_description))

            withStyle(
                style = SpanStyle(
                    textDecoration = TextDecoration.Underline,
                    fontWeight = MBTheme.typography.body.textMedium.fontWeight
                )
            ) {
                append(" " + stringResource(StringResource.auth_sign_up))
            }
        }

        ClickableText(
            text = signUpText,
            style = MBTheme.typography.body.text,
            onClick = { onClick() }
        )

        Divider(
            modifier = Modifier.weight(1f),
            color = MBTheme.colors.foreground.divider
        )
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES, name = "AuthScreen DARK")
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO, name = "AuthScreen LIGHT")
@Composable
private fun AuthContentPreview() {
    MBTheme() {
        AuthContent(
            state = AuthViewState(),
            onBackPress = {},
            updateLogin = {},
            updatePassword = {}
        )
    }
}
