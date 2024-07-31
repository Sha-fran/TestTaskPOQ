package com.testtaskpoq.data

import com.testtaskpoq.core.network.POQTestTaskApiInterface
import com.testtaskpoq.data.mappers.toEntity
import com.testtaskpoq.domain.entity.ListOfOrganisationsEntity
import com.testtaskpoq.domain.repositories.ListOfOrganisationsRepository
import javax.inject.Inject

class ListOfOrganisationsRepositoryImpl @Inject constructor(
    private val poqApiInterface: POQTestTaskApiInterface
) : ListOfOrganisationsRepository {

    override suspend fun getListOfOrganisations(): List<ListOfOrganisationsEntity> =
        poqApiInterface.getListOfOrganisations().map { it.toEntity() }
}