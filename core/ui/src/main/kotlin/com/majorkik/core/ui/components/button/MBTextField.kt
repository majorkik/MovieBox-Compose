package com.majorkik.core.ui.components.button

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.takeOrElse
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.majorkik.core.ui.theme.MBTheme
import com.majorkik.core.ui.theme.ThemePreview

@Composable
fun MBPasswordTextField(
    value: String,
    hint: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = MBTheme.typography.body.medium,
    color: Color = MBTheme.colors.text.primary,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    shape: Shape = RoundedCornerShape(12.dp),
) {
    MBTextField(
        value = value,
        hint = hint,
        onValueChange = onValueChange,
        modifier = modifier,
        visualTransformation = PasswordVisualTransformation(),
        enabled = enabled,
        readOnly = readOnly,
        textStyle = textStyle,
        color = color,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        interactionSource = interactionSource,
        shape = shape
    )
}

@Composable
fun MBTextField(
    value: String,
    hint: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = MBTheme.typography.body.medium,
    color: Color = MBTheme.colors.text.primary,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    shape: Shape = RoundedCornerShape(12.dp),
) {
    // If color is not provided via the text style, use content color as a default
    val textColor = textStyle.color.takeOrElse { color }
    val mergedTextStyle = textStyle.merge(TextStyle(color = textColor))

    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.height(48.dp),
        enabled = enabled,
        readOnly = readOnly,
        textStyle = mergedTextStyle,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        singleLine = true,
        cursorBrush = SolidColor(MBTheme.colors.foreground.accent),
        interactionSource = interactionSource,
        decorationBox = { innerTextField ->
            DecorationBox(
                value = value,
                hint = hint,
                innerTextField = innerTextField,
                shape = shape,
                interactionSource = interactionSource,
            )
        },
    )
}

@Composable
private fun DecorationBox(
    value: String,
    hint: String,
    innerTextField: @Composable () -> Unit,
    shape: Shape,
    interactionSource: MutableInteractionSource,
) {
    val isFocused by interactionSource.collectIsFocusedAsState()

    Box(
        modifier = Modifier
            .clip(shape)
            .border(
                width = 2.dp,
                color = if (isFocused) {
                    MBTheme.colors.foreground.accent
                } else {
                    MBTheme.colors.foreground.transparent
                },
                shape = shape
            )
            .background(MBTheme.colors.background.elevation1)
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        if (value.isEmpty()) {
            Text(
                text = hint,
                color = MBTheme.colors.foreground.infoAccent,
                style = MBTheme.typography.body.text
            )
        }

        innerTextField()
    }
}

@ThemePreview
@Composable
private fun MBTextFieldPreview(
    @PreviewParameter(MBTextFieldProvider::class) text: Pair<String, VisualTransformation>,
) {
    MBTheme {
        Box(modifier = Modifier.padding(8.dp)) {
            MBTextField(
                value = text.first,
                hint = "Hint",
                onValueChange = { },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = text.second
            )
        }
    }
}

private class MBTextFieldProvider : PreviewParameterProvider<Pair<String, VisualTransformation>> {
    override val values = sequenceOf(
        "simplelogin" to VisualTransformation.None,
        "" to VisualTransformation.None,
        "password" to PasswordVisualTransformation()
    )

}
