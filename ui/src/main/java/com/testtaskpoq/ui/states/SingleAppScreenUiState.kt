package com.testtaskpoq.ui.states

import com.testtaskpoq.domain.entity.ListOfOrganisationsEntity

data class SingleAppScreenUiState(
    val listOfOrganisations: List<ListOfOrganisationsEntity>,
    val errorMessage: String
) {
    companion object {
        val empty = SingleAppScreenUiState(
            listOfOrganisations = listOf(),
            errorMessage = ""
        )
    }
}