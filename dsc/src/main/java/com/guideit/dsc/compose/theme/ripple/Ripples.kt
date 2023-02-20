package com.guideit.dsc.compose.theme.ripple

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import com.guideit.dsc.compose.theme.color.ColorPalette

@Composable
fun rippleEffect(
    color: Color = ColorPalette.Primary100
) = rememberRipple(color = color)

fun Modifier.rippleClickable(
    interactionSource: MutableInteractionSource? = null,
    shape: Shape = RectangleShape,
    enabled: Boolean = true,
    onClick: () -> Unit
) = composed {
    clip(shape).clickable(
        enabled = enabled,
        interactionSource = interactionSource ?: remember { MutableInteractionSource() },
        indication = rippleEffect(),
        onClick = onClick
    )
}