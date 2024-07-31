package com.testtaskpoq.domain.repositories

import com.testtaskpoq.domain.entity.ListOfOrganisationsEntity

interface ListOfOrganisationsRepository {
    suspend fun getListOfOrganisations(): List<ListOfOrganisationsEntity>
}