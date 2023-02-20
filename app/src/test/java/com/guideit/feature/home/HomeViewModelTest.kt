package com.guideit.feature.home

import com.guideit.CoroutinesTestRule
import com.guideit.feature.home.HomeViewModel.ScreenEvent
import com.guideit.feature.main.MainViewModel.*
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineScope
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeViewModelTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private val testCoroutineScope = TestCoroutineScope(Job())

    private val observeEventMock: (ScreenEvent) -> (Unit) = mockk(relaxed = true)

    private lateinit var viewModel: HomeViewModel

    @Before
    fun setup() {
        viewModel = HomeViewModel()
        prepareEventObserver()
    }

    @Test
    fun `when clicked goInformationClicked then dispatch event InformationOption`() {
        // when
        viewModel.goInformationClicked()

        // then
        verify { observeEventMock(ScreenEvent.InformationOption) }
    }

    @Test
    fun `when clicked goChartClicked then dispatch event Navigation`() {
        // when
        viewModel.goChartClicked()

        // then
        verify { observeEventMock(ScreenEvent.NavigateTo(Navigation.GraphScreen)) }
    }

    private fun prepareEventObserver() = testCoroutineScope.run {
        launch { viewModel.eventsFlow.collect { observeEventMock(it) } }
    }
}