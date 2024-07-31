package com.testtaskpoq.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.testtaskpoq.core.network.di.DispatcherProvider
import com.testtaskpoq.domain.GetListOfOrganisationsUseCase
import com.testtaskpoq.ui.states.SingleAppScreenUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

const val ERROR_MESSAGE = "Failed to fetch repositories:"

@HiltViewModel
class SingleAppScreenViewModel @Inject constructor(
    private val getListOfOrganisationsUseCase: GetListOfOrganisationsUseCase,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {

    private val _listOfOrganisationsState = MutableStateFlow(SingleAppScreenUiState.empty)
    var listOfOrganisationsState = _listOfOrganisationsState.asStateFlow()

    init {
        getListOfRepositories()
    }

    private fun getListOfRepositories() {

        viewModelScope.launch(dispatcherProvider.io) {
            try {
                _listOfOrganisationsState.update {
                    it.copy(
                        listOfOrganisations = getListOfOrganisationsUseCase.getListOfOrganisations()
                    )
                }
            } catch (e: Exception) {
                _listOfOrganisationsState.update {
                    it.copy(
                        listOfOrganisations = listOf(),
                        errorMessage = "$ERROR_MESSAGE ${e.message}"
                    )
                }
            }
        }
    }
}