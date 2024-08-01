package com.testtaskpoq.ui

import com.testtaskpoq.core.network.di.DispatcherProvider
import com.testtaskpoq.domain.GetListOfOrganisationsUseCase
import com.testtaskpoq.domain.entity.ListOfOrganisationsEntity
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
    fun when_response_is_successful() = runTest {
        val mockOrganisations = listOf(
            ListOfOrganisationsEntity("Org1", "Description1"),
            ListOfOrganisationsEntity("Org2", "Description2")
        )
        coEvery { getListOfOrganisationsUseCase.getListOfOrganisations() } returns mockOrganisations

        viewModel.getListOfRepositories()

        testDispatcher.scheduler.advanceUntilIdle()

        val state = viewModel.listOfOrganisationsState.value
        assert(state.listOfOrganisations == mockOrganisations)
    }
}