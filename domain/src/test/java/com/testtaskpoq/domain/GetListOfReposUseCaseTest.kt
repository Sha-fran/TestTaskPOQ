package com.testtaskpoq.domain

import com.testtaskpoq.domain.entity.ListOfReposEntity
import com.testtaskpoq.domain.repositories.ListOfReposRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetListOfReposUseCaseTest {

    @MockK
    private lateinit var mockRepository: ListOfReposRepository

    private lateinit var useCase: GetListOfReposUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        useCase = GetListOfReposUseCase(mockRepository)
    }

    @Test
    fun getListOfRepos_returns_list_from_repository() = runBlocking {
        val expectedList = listOf(
            ListOfReposEntity(nameOfRepo = "1", descriptionOfRepo = "Org1"),
            ListOfReposEntity(nameOfRepo = "2", descriptionOfRepo = "Org2")
        )

        coEvery { mockRepository.getListOfRepos() } returns expectedList

        val result = useCase.getListOfRepos()

        coVerify { mockRepository.getListOfRepos() }
        assertEquals(expectedList, result)
    }
}