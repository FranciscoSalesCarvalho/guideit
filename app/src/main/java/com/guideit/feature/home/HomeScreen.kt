package com.guideit.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.guideit.R
import com.guideit.dsc.compose.component.Button
import com.guideit.dsc.compose.component.ButtonWidth
import com.guideit.dsc.compose.component.SpacerVertical
import com.guideit.dsc.compose.theme.DscTheme
import com.guideit.dsc.compose.theme.color.ColorPalette
import com.guideit.dsc.compose.theme.dimen.Size
import com.guideit.feature.main.MainActivity
import com.guideit.feature.main.MainViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    flowViewModel: MainViewModel
) {

    val activity = LocalContext.current as MainActivity

    EventConsumer(
        activity = activity,
        viewModel = viewModel,
        flowViewModel = flowViewModel
    )

    Screen(
        onGoInformationClicked = viewModel::goInformationClicked,
        onGoChartClicked = viewModel::goChartClicked
    )
}

@Composable
private fun EventConsumer(
    activity: MainActivity,
    viewModel: HomeViewModel,
    flowViewModel: MainViewModel
) {
    LaunchedEffect(key1 = viewModel) {
        viewModel.eventsFlow.collect { event ->
            when(event) {
                HomeViewModel.ScreenEvent.InformationOption -> activity.goInformation()
                is HomeViewModel.ScreenEvent.NavigateTo -> flowViewModel.navigate(event.navigation)
            }
        }
    }
}

@Composable
private fun Screen(
    onGoInformationClicked: () -> Unit,
    onGoChartClicked: () -> Unit
) = DscTheme {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ColorPalette.Support100)
            .padding(Size.SizeSM),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            text = stringResource(id = R.string.option_information),
            onClick = onGoInformationClicked,
            buttonWidth = ButtonWidth.fill(),
        )
        SpacerVertical()
        Button(
            text = stringResource(id = R.string.option_chart),
            onClick = onGoChartClicked,
            buttonWidth = ButtonWidth.fill(),
        )
    }
}