package com.guideit.feature.main

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.guideit.ENGINE_ID
import com.guideit.commons.extensions.composeNavigate
import com.guideit.dsc.R
import com.guideit.commons.extensions.setStatusBarColor
import com.guideit.commons.navigation.setNavigationContent
import com.guideit.commons.view.BaseComposeActivity
import com.guideit.feature.graph.GraphScreen
import com.guideit.feature.home.HomeScreen
import com.guideit.feature.main.MainViewModel.Navigation
import io.flutter.embedding.android.FlutterActivity

class MainActivity : BaseComposeActivity<MainViewModel>() {

    override fun viewModelClass(): Class<MainViewModel> = MainViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStatusBarColor(R.color.support100)
        setNavigationContent(
            startDestination = Navigation.HomeScreen.route,
            navGraphBuilder = this::navGraphBuilder,
            navEventFlow = flowViewModel.eventsFlow,
            navEvent = this::navEvent
        )
    }

    private fun navGraphBuilder(builder: NavGraphBuilder) = builder.apply {
        composable(Navigation.HomeScreen.route) {
            HomeScreen(composeViewModel(), flowViewModel)
        }
        composable(Navigation.GraphScreen.route) {
            GraphScreen(composeViewModel())
        }
    }

    private fun navEvent(navController: NavController, navScreen: Navigation) {
        navController.composeNavigate(
            route = navScreen.route,
            popStack = navScreen.popStack
        )
    }

    internal fun goInformation() {
        startActivity(
            FlutterActivity.withCachedEngine(ENGINE_ID)
                .build(this@MainActivity)
        )
    }
}