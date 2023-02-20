package com.guideit.feature.graph

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.guideit.R
import com.guideit.domain.model.QuoteSymbol
import com.guideit.dsc.compose.component.Header
import com.guideit.dsc.compose.theme.DscTheme
import com.guideit.dsc.compose.theme.color.ColorPalette
import com.guideit.dsc.compose.theme.dimen.Size
import com.guideit.feature.graph.GraphViewModel.*
import com.guideit.feature.main.MainActivity

@Composable
fun GraphScreen(
    viewModel: GraphViewModel
) {

    val activity = LocalContext.current as MainActivity

    LaunchedEffect(viewModel) {
        viewModel.setup()
    }

    EventConsumer(
        activity = activity,
        viewModel = viewModel,
    )

    Screen(
        uiState = viewModel.uiState,
        onGoBackClicked = viewModel::onGoBackClicked,
        onRetryClicked = viewModel::setup
    )
}

@Composable
private fun EventConsumer(
    activity: MainActivity,
    viewModel: GraphViewModel
) {
    LaunchedEffect(key1 = viewModel) {
        viewModel.eventsFlow.collect { event ->
            when (event) {
                ScreenEvent.GoBack -> activity.onBackPressed()
            }
        }
    }
}

@Composable
private fun Screen(
    uiState: GraphScreenUiState,
    onGoBackClicked: () -> Unit,
    onRetryClicked: () -> Unit,
) = DscTheme {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ColorPalette.Support100)
            .padding(Size.SizeSM),
    ) {
        PageHeader(onGoBackClicked = onGoBackClicked)
        when (val result = uiState.screenState.collectAsState().value) {
            is ScreenState.Failure -> ScreenError(
                error = result.error,
                onRetryClicked = onRetryClicked,
                onErrorCloseClicked = onGoBackClicked
            )
            ScreenState.Loading -> ScreenProgress()
            is ScreenState.Success -> ScreenContent(result.stock)
        }
    }
}

@Composable
private fun PageHeader(
    onGoBackClicked: () -> Unit
) {
    Header(
        title = stringResource(id = R.string.option_chart),
        fontIconStart = stringResource(id = com.guideit.dsc.R.string.icon_long_arrow_left_kit),
        fontIconStartClick = onGoBackClicked
    )
}

@Composable
fun ScreenContent(data: QuoteSymbol) {
    CandlesticksChartBody(data = listOf(data))
}

