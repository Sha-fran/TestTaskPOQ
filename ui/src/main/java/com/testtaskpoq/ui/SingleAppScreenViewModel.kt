package com.testtaskpoq.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.testtaskpoq.core.network.di.DispatcherProvider
import com.testtaskpoq.domain.GetListOfReposUseCase
import com.testtaskpoq.ui.states.SingleAppScreenUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SingleAppScreenViewModel @Inject constructor(
    private val getListOfReposUseCase: GetListOfReposUseCase,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {

    private val _listOfReposState =
        MutableStateFlow<SingleAppScreenUiState>(SingleAppScreenUiState.Loading)
    var listOfReposState = _listOfReposState.asStateFlow()

    init {
        getListOfRepositories()
    }

    fun getListOfRepositories() {

        _listOfReposState.value = SingleAppScreenUiState.Loading

        viewModelScope.launch(dispatcherProvider.io) {
            try {
                val repos = getListOfReposUseCase.getListOfRepos()
                _listOfReposState.value = SingleAppScreenUiState.Success(repos)
            } catch (e: Exception) {
                _listOfReposState.value = SingleAppScreenUiState.Error
            }
        }
    }
}