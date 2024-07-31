package com.testtaskpoq.domain

import com.testtaskpoq.domain.entity.ListOfOrganisationsEntity
import com.testtaskpoq.domain.repositories.ListOfOrganisationsRepository
import javax.inject.Inject

class GetListOfOrganisationsUseCase @Inject constructor(
    private val listOfOrganisationsRepository: ListOfOrganisationsRepository
) {
    suspend fun getListOfOrganisations(): List<ListOfOrganisationsEntity> =
        listOfOrganisationsRepository.getListOfOrganisations()
}