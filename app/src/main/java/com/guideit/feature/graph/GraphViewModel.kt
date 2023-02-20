package com.guideit.feature.graph

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.guideit.commons.error.Error
import com.guideit.commons.extensions.Result
import com.guideit.commons.viewmodel.ChannelEventSenderImpl
import com.guideit.commons.viewmodel.EventSender
import com.guideit.domain.model.QuoteSymbol
import com.guideit.domain.usecase.FinanceUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class GraphViewModel @Inject constructor(
    private val useCase: FinanceUseCase
) : ViewModel(), EventSender<GraphViewModel.ScreenEvent> by ChannelEventSenderImpl() {

    val uiState = GraphScreenUiState()

    fun setup() {
        fetchStock()
    }

    fun onGoBackClicked() {
        viewModelScope.sendEvent(ScreenEvent.GoBack)
    }

    private fun fetchStock() = viewModelScope.launch {
        uiState.screenState.value = ScreenState.Loading
        when (val result = useCase.execute()) {
            is Result.Failure ->
                uiState.screenState.value = ScreenState.Failure(result.error)
            is Result.Success ->
                uiState.screenState.value = ScreenState.Success(result.data)
        }
    }

    class GraphScreenUiState {
        val screenState = MutableStateFlow<ScreenState>(ScreenState.Loading)
    }

    sealed class ScreenState {
        data class Success(val stock: QuoteSymbol) : ScreenState()
        object Loading : ScreenState()
        data class Failure(val error: Error) : ScreenState()
    }

    sealed class ScreenEvent {
        object GoBack : ScreenEvent()
    }
}