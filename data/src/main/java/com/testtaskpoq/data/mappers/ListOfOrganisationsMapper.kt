package com.testtaskpoq.data.mappers

import com.testtaskpoq.core.network.models.ListOfOrganisationsModel
import com.testtaskpoq.domain.entity.ListOfOrganisationsEntity

fun ListOfOrganisationsModel.toEntity(): ListOfOrganisationsEntity =
    ListOfOrganisationsEntity(
        nameOfOrganisation = this.name,
        descriptionOfOrganisation = this.let { description } ?: "Description is absent"
    )