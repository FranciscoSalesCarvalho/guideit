package com.guideit.feature.graph

import android.graphics.drawable.Drawable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.fragment.app.FragmentActivity
import com.guideit.R
import com.guideit.commons.error.Error
import com.guideit.dsc.component.dialog.dsl.showErrorFullDialog
import com.guideit.dsc.compose.component.LabelValueText
import com.guideit.dsc.compose.component.ProgressAnimationMessage
import com.guideit.dsc.compose.component.ProgressAnimationType
import com.guideit.dsc.compose.theme.color.ColorPalette
import com.guideit.dsc.compose.theme.dimen.ProgressSize
import com.guideit.dsc.compose.theme.font.Font
import com.guideit.commons.extensions.EMPTY_STRING

@Composable
internal fun ScreenProgress(
    message: String = stringResource(id = R.string.loading_progress_title),
    description: String = stringResource(id = R.string.loading_progress_subtitle),
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(ColorPalette.Support100),
        contentAlignment = Alignment.Center,

        ) {
        ProgressAnimationMessage(
            modifier = Modifier.size(ProgressSize.ProgressXLG),
            type = ProgressAnimationType.COG_PRIMARY200,
            message = LabelValueText(
                text = message,
                textStyle = Font.h3
            ),
            description = LabelValueText(
                text = description,
                textStyle = Font.body1,
                color = ColorPalette.Support500
            ),
        )
    }
}

@Composable
internal fun ScreenError(
    error: Error,
    onRetryClicked: () -> Unit,
    onErrorCloseClicked: () -> Unit
) {
    GuideItErrorDialog(
        title = error.title,
        message = error.message,
        bottomButtonText = stringResource(id = R.string.retry),
        onBottomButtonClick = onRetryClicked,
        onCloseClick = onErrorCloseClicked
    )
}

@Composable
private fun GuideItErrorDialog(
    icon: String = EMPTY_STRING,
    title: String = EMPTY_STRING,
    message: String = EMPTY_STRING,
    topButtonText: String = EMPTY_STRING,
    topButtonIcon: Drawable? = null,
    onTopButtonClick: () -> Unit = {},
    bottomButtonText: String = EMPTY_STRING,
    onBottomButtonClick: () -> Unit = {},
    onCloseClick: () -> Unit = {},
    cancelable: Boolean = false
) {
    val activity = LocalContext.current as FragmentActivity
    DisposableEffect(Unit) {
        val dialog = activity.showErrorFullDialog {
            this.icon = icon
            this.title = title
            this.message = message
            this.topButtonText = topButtonText
            this.topButtonIcon = topButtonIcon
            this.onTopButtonClick = onTopButtonClick
            this.bottomButtonText = bottomButtonText
            this.onBottomButtonClick = onBottomButtonClick
            this.onCloseClick = onCloseClick
            this.cancelable = cancelable
        }
        onDispose {
            dialog.dismiss()
        }
    }
}
