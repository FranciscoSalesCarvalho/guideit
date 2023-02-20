package com.guideit.feature.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.guideit.commons.extensions.EMPTY_STRING
import com.guideit.commons.viewmodel.ChannelEventSenderImpl
import com.guideit.commons.viewmodel.EventSender
import javax.inject.Inject

class MainViewModel @Inject constructor() : ViewModel(),
    EventSender<MainViewModel.Navigation> by ChannelEventSenderImpl() {

    fun navigate(navigation: Navigation) {
        viewModelScope.sendEvent(navigation)
    }

    sealed class Navigation(
        val route: String = EMPTY_STRING,
        val popStack: Boolean = false
    ) {
        object HomeScreen : Navigation("home_screen")
        object GraphScreen : Navigation("graph_screen")
    }
}