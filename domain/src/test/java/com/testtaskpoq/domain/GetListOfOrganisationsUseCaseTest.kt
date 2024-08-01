package com.testtaskpoq.domain

import com.testtaskpoq.domain.entity.ListOfOrganisationsEntity
import com.testtaskpoq.domain.repositories.ListOfOrganisationsRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

class GetListOfOrganisationsUseCaseTest {
    @Mock
    private lateinit var mockRepository: ListOfOrganisationsRepository

    private lateinit var useCase: GetListOfOrganisationsUseCase

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        useCase = GetListOfOrganisationsUseCase(mockRepository)
    }

    @Test
    fun getListOfOrganisations_returns_list_from_repository() = runBlocking {
        val expectedList = listOf(
            ListOfOrganisationsEntity(nameOfOrganisation = "1", descriptionOfOrganisation = "Org1"),
            ListOfOrganisationsEntity(nameOfOrganisation = "2", descriptionOfOrganisation = "Org2")
        )
        whenever(mockRepository.getListOfOrganisations()).thenReturn(expectedList)

        val result = useCase.getListOfOrganisations()

        assertEquals(expectedList, result)
    }
}