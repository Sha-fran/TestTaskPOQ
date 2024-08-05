package com.testtaskpoq.ui.states

import com.testtaskpoq.domain.entity.ListOfReposEntity

sealed class SingleAppScreenUiState {
    data object Loading : SingleAppScreenUiState()
    data class Success(val listOfRepos: List<ListOfReposEntity>) : SingleAppScreenUiState()
    data object Error : SingleAppScreenUiState()
}