package com.testtaskpoq.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.testtaskpoq.ui.components.ItemCard
import com.testtaskpoq.ui.components.POQLoader

@Composable
fun SingleAppScreen(
    singleAppScreenViewModel: SingleAppScreenViewModel = hiltViewModel()
) {
    val uiState by singleAppScreenViewModel.listOfOrganisationsState.collectAsState()

    Surface(
        color = Color.White
    ) {
        Scaffold(
            modifier = Modifier.systemBarsPadding()
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Spacer(modifier = Modifier.padding(paddingValues))

                when (uiState.errorState) {
                    false -> LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(vertical = 32.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        itemsIndexed(uiState.listOfOrganisations) { _, organisation ->
                            ItemCard(
                                name = organisation.nameOfOrganisation,
                                description = organisation.descriptionOfOrganisation
                            )
                        }
                    }
                    true -> Text(text = stringResource(id = R.string.failed_to_fetch_repositories))
                }
                if (uiState.loaderState) {
                    POQLoader()
                }
            }
        }
    }
}