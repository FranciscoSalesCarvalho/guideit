package com.guideit.commons.extensions

import androidx.navigation.NavController
import com.guideit.common.R

fun NavController.composeNavigate(
    route: String,
    popStack: Boolean = false,
    launchSingleTop: Boolean = true
) = navigate(route = route) {
    val startDestinationRoute = graph.startDestinationRoute.orEmpty()
    if (popStack && startDestinationRoute.isNotEmpty()) {
        popUpTo(startDestinationRoute) { inclusive = true }
    }
    this.launchSingleTop = launchSingleTop
    anim {
        enter = R.anim.fast_fade_in
        exit = R.anim.fast_fade_out
    }
}