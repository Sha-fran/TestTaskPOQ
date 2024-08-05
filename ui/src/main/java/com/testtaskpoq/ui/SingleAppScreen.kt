package com.testtaskpoq.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.testtaskpoq.ui.components.ErrorStateComponent
import com.testtaskpoq.ui.components.ItemCard
import com.testtaskpoq.ui.components.POQLoader
import com.testtaskpoq.ui.states.SingleAppScreenUiState

@Composable
fun SingleAppScreen(
    singleAppScreenViewModel: SingleAppScreenViewModel = hiltViewModel()
) {
    val uiState by singleAppScreenViewModel.listOfReposState.collectAsState()

    Surface(
        color = Color.White
    ) {
        Scaffold(
            modifier = Modifier.systemBarsPadding()
        ) { paddingValues ->
            Spacer(modifier = Modifier.padding(paddingValues))

            when (uiState) {
                is SingleAppScreenUiState.Loading -> {
                    POQLoader()
                }

                is SingleAppScreenUiState.Success -> {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentPadding = PaddingValues(vertical = 32.dp, horizontal = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        itemsIndexed((uiState as SingleAppScreenUiState.Success).listOfRepos) { _, repos ->
                            ItemCard(
                                name = repos.nameOfRepo,
                                description = repos.descriptionOfRepo
                            )
                        }
                    }
                }

                is SingleAppScreenUiState.Error -> {
                    ErrorStateComponent(
                        onClick = { singleAppScreenViewModel.getListOfRepositories() }
                    )
                }
            }
        }
    }
}