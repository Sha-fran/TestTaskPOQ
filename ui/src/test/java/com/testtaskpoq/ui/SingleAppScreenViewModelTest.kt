package com.testtaskpoq.ui

import com.testtaskpoq.core.network.di.DispatcherProvider
import com.testtaskpoq.domain.GetListOfOrganisationsUseCase
import com.testtaskpoq.domain.entity.ListOfOrganisationsEntity
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

class SingleAppScreenViewModelTest {
    @MockK
    private lateinit var getListOfOrganisationsUseCase: GetListOfOrganisationsUseCase

    @MockK
    private lateinit var dispatcherProvider: DispatcherProvider

    private lateinit var viewModel: SingleAppScreenViewModel

    private val testDispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(testDispatcher)

        coEvery { dispatcherProvider.main } returns testDispatcher
        coEvery { dispatcherProvider.io } returns testDispatcher
        coEvery { dispatcherProvider.default } returns testDispatcher

        viewModel = SingleAppScreenViewModel(getListOfOrganisationsUseCase, dispatcherProvider)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun when_response_is_successful_loader_is_hidden_and_error_is_not_shown() = runTest {
        val sampleOrganisations = listOf(
            ListOfOrganisationsEntity("Org1", "Description1"),
            ListOfOrganisationsEntity("Org2", "Description2")
        )
        coEvery { getListOfOrganisationsUseCase.getListOfOrganisations() } returns sampleOrganisations

        viewModel.getListOfRepositories()

        testDispatcher.scheduler.advanceUntilIdle()

        val state = viewModel.listOfOrganisationsState.value
        assert(state.listOfOrganisations == sampleOrganisations)
        assert(!state.errorState)
        assert(!state.loaderState)
    }

    @Test
    fun when_response_fails_error_is_shown_and_loader_is_hidden() = runTest {
        coEvery { getListOfOrganisationsUseCase.getListOfOrganisations() } throws Exception("Network error")

        viewModel.getListOfRepositories()

        testDispatcher.scheduler.advanceUntilIdle()

        val state = viewModel.listOfOrganisationsState.value
        assert(state.listOfOrganisations.isEmpty())
        assert(state.errorState)
        assert(!state.loaderState)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun loader_is_shown_while_fetching_data() = runTest {
        val sampleOrganisations = listOf(ListOfOrganisationsEntity("Org1", "Description1"))

        coEvery { getListOfOrganisationsUseCase.getListOfOrganisations() } coAnswers {
            delay(1000)
            sampleOrganisations
        }

        viewModel.getListOfRepositories()

        assert(viewModel.listOfOrganisationsState.value.loaderState)

        testDispatcher.scheduler.advanceTimeBy(500)
        assert(viewModel.listOfOrganisationsState.value.loaderState)

        testDispatcher.scheduler.advanceUntilIdle()

        val finalState = viewModel.listOfOrganisationsState.value
        assert(finalState.listOfOrganisations == sampleOrganisations)
        assert(!finalState.errorState)
        assert(!finalState.loaderState)
    }
}