package com.guideit.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.guideit.commons.viewmodel.ChannelEventSenderImpl
import com.guideit.commons.viewmodel.EventSender
import com.guideit.feature.main.MainViewModel.Navigation
import javax.inject.Inject

class HomeViewModel @Inject constructor() : ViewModel(),
    EventSender<HomeViewModel.ScreenEvent> by ChannelEventSenderImpl() {

    fun goInformationClicked() {
        viewModelScope.sendEvent(ScreenEvent.InformationOption)
    }

    fun goChartClicked() {
        viewModelScope.sendEvent(ScreenEvent.NavigateTo(Navigation.GraphScreen))
    }

    sealed class ScreenEvent {
        object InformationOption : ScreenEvent()
        data class NavigateTo(val navigation: Navigation) : ScreenEvent()
    }
}