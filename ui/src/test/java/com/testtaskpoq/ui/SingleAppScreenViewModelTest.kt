package com.testtaskpoq.ui

import com.testtaskpoq.core.network.di.DispatcherProvider
import com.testtaskpoq.domain.GetListOfReposUseCase
import com.testtaskpoq.domain.entity.ListOfReposEntity
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
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class SingleAppScreenViewModelTest {
    @MockK
    private lateinit var getListOfReposUseCase: GetListOfReposUseCase

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

        viewModel = SingleAppScreenViewModel(getListOfReposUseCase, dispatcherProvider)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun when_response_is_successful_loader_is_hidden_and_error_is_not_shown() = runTest {
        val sampleRepos = listOf(
            ListOfReposEntity("Org1", "Description1"),
            ListOfReposEntity("Org2", "Description2")
        )
        coEvery { getListOfReposUseCase.getListOfRepos() } returns sampleRepos

        viewModel.getListOfRepositories()

        testDispatcher.scheduler.advanceUntilIdle()

        val state = viewModel.listOfReposState.value
        assertEquals(state.listOfRepos, sampleRepos)
        assert(!state.isError)
        assert(!state.isLoaderActive)
    }

    @Test
    fun when_response_fails_error_is_shown_and_loader_is_hidden() = runTest {
        coEvery { getListOfReposUseCase.getListOfRepos() } throws Exception("Network error")

        viewModel.getListOfRepositories()

        testDispatcher.scheduler.advanceUntilIdle()

        val state = viewModel.listOfReposState.value
        assert(state.listOfRepos.isEmpty())
        assert(state.isError)
        assert(!state.isLoaderActive)
    }

    @Test
    fun loader_is_shown_while_fetching_data() = runTest {
        val sampleRepos = listOf(ListOfReposEntity("Org1", "Description1"))

        coEvery { getListOfReposUseCase.getListOfRepos() } coAnswers {
            delay(1000)
            sampleRepos
        }

        viewModel.getListOfRepositories()

        assert(viewModel.listOfReposState.value.isLoaderActive)

        testDispatcher.scheduler.advanceUntilIdle()

        val state = viewModel.listOfReposState.value
        assertEquals(state.listOfRepos, sampleRepos)
        assert(!state.isError)
        assert(!state.isLoaderActive)
    }
}