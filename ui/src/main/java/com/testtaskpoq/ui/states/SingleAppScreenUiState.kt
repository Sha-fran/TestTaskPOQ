package com.testtaskpoq.ui.states

import com.testtaskpoq.domain.entity.ListOfOrganisationsEntity

data class SingleAppScreenUiState(
    val listOfOrganisations: List<ListOfOrganisationsEntity> = listOf(),
    val errorState: Boolean = false,
    val loaderState: Boolean = true
)