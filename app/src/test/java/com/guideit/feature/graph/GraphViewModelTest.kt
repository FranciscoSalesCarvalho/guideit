package com.guideit.feature.graph

import com.guideit.CoroutinesTestRule
import com.guideit.commons.error.Error
import com.guideit.commons.extensions.Result
import com.guideit.domain.model.QuoteSymbol
import com.guideit.domain.usecase.FinanceUseCase
import com.guideit.feature.graph.GraphViewModel.*
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import io.mockk.verifyOrder
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineScope
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class GraphViewModelTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private val testCoroutineScope = TestCoroutineScope(Job())

    private val observeEventMock: (ScreenEvent) -> (Unit) = mockk(relaxed = true)
    private val screenState: (ScreenState) -> Unit = mockk(relaxed = true)

    private val useCase: FinanceUseCase = mockk(relaxed = true)

    private lateinit var viewModel: GraphViewModel

    @Before
    fun setup() {
        viewModel = GraphViewModel(useCase = useCase)
        prepareEventObserver()
    }

    @Test
    fun `when clicked onGoBackClicked then dispatch event GoBack`() {
        // when
        viewModel.onGoBackClicked()

        // then
        verify { observeEventMock(ScreenEvent.GoBack) }
    }

    @Test
    fun `when clicked onResultLauncherResult should fetch stock successful`() = runBlocking {
        val stock = QuoteSymbol.mock()
        coEvery {
            useCase.execute()
        } returns Result.Success(data = stock)

        viewModel.setup()

        verifyOrder {
            screenState(ScreenState.Loading)
            screenState(ScreenState.Success(stock = stock))
        }
    }

    @Test
    fun `when clicked onResultLauncherResult should fetch posts failure`() = runBlocking {
        val error: Error = mockk(relaxed = true)
        coEvery {
            useCase.execute()
        } returns Result.Failure(error = error)

        viewModel.setup()

        verifyOrder {
            screenState(ScreenState.Loading)
            screenState(ScreenState.Failure(error = error))
        }
    }

    private fun prepareEventObserver() = testCoroutineScope.run {
        launch { viewModel.eventsFlow.collect { observeEventMock(it) } }
        launch { viewModel.uiState.screenState.collect { screenState(it) } }
    }
}