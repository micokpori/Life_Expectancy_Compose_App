package com.rfcreations.lifeexpectancychecker.ui.home_screen

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.rfcreations.lifeexpectancychecker.R
import com.rfcreations.lifeexpectancychecker.ui.home_screen.advanced.AdvancedBottomSheetLifeExpResult
import com.rfcreations.lifeexpectancychecker.ui.home_screen.advanced.AdvancedContent
import com.rfcreations.lifeexpectancychecker.ui.home_screen.advanced.AdvancedUiEvent
import com.rfcreations.lifeexpectancychecker.ui.home_screen.basic.BasicBottomSheetLifeExpResult
import com.rfcreations.lifeexpectancychecker.ui.home_screen.basic.BasicContent
import com.rfcreations.lifeexpectancychecker.ui.home_screen.basic.BasicUiEvent
import com.rfcreations.lifeexpectancychecker.ui.home_screen.components.AppBar
import com.rfcreations.lifeexpectancychecker.ui.home_screen.components.AppThemeDialog
import com.rfcreations.lifeexpectancychecker.ui.home_screen.components.CalculateFabButton

/**
 * Top level composable
 * entry home_screen of this app
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(homeViewModel: HomeViewModel = hiltViewModel()) {

    val basicUiState = homeViewModel.basicUiState.collectAsState().value
    val advancedUiState = homeViewModel.advancedUiState.collectAsState().value
    val showAppThemeDialog = homeViewModel.showAppThemeDialog.collectAsState()
    val showBasicResultSheet = homeViewModel.showBasicResultSheet.collectAsState()
    val showAdvancedResultSheet = homeViewModel.showAdvancedResultSheet.collectAsState()
    val tabOptions = stringArrayResource(id = R.array.tab_options)
    var selectedTab by rememberSaveable { mutableIntStateOf(0) }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(0.5f),
        topBar = {
            AppBar(
                toggleShowAppThemeDialog = { homeViewModel.toggleShowAppThemeDialog() },
                clearAllSelections = {
                    homeViewModel.resetUiSelections()
                }
            )
        },
        floatingActionButton = {
            CalculateFabButton(
                checkAllOptionsAreSelected = {
                    if (selectedTab == 0)
                        homeViewModel.shouldShowBasicReminderToast()
                    else homeViewModel.shouldShowAdvancedReminderToast()
                },
                showResultSheet = {
                    if (selectedTab == 0) {
                        homeViewModel.basicUiEvent(BasicUiEvent.ToggleShowResultSheet)
                    } else {
                        homeViewModel.advancedUiEvent(AdvancedUiEvent.ToggleShowAdvancedResultSheet)
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            PrimaryTabRow(
                selectedTabIndex = selectedTab
            ) {
                tabOptions.forEachIndexed { index, string ->
                    Tab(
                        selected = index == selectedTab,
                        text = { Text(text = string) },
                        onClick = {
                            selectedTab = index
                            Log.d("SelectedTab", "$index")
                        }
                    )
                }
            }
            if (selectedTab == 0) {
                BasicContent(
                    homeViewModel,
                    modifier = Modifier.fillMaxWidth()
                )
            } else {
                AdvancedContent(
                    homeViewModel,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        if (showAppThemeDialog.value) {
            AppThemeDialog(themeUiState = homeViewModel.themeUiState) {
                homeViewModel.toggleShowAppThemeDialog()
            }
        }
        if (showBasicResultSheet.value) {
            BasicBottomSheetLifeExpResult(
                selectedCountry = basicUiState.selectedCountry,
                genderSelection = basicUiState.genderSelection,
                smokeSelection = basicUiState.smokeSelection,
                socialClassSelection = basicUiState.socialClassSelection,
                educationSelection = basicUiState.educationSelection,
                relationShipStatus = basicUiState.relationShipStatus,
                diabeticSelection = basicUiState.diabeticSelection,
                jobSelection = basicUiState.jobSelection,
                bmiSelection = basicUiState.bmiSelection,
                exerciseSelection = basicUiState.exerciseSelection,
                modifier = Modifier.fillMaxWidth(),
                toggleShowResultSheet = { homeViewModel.basicUiEvent(BasicUiEvent.ToggleShowResultSheet) }
            )
        }
        if (showAdvancedResultSheet.value) {
            AdvancedBottomSheetLifeExpResult(
                generativeModel = homeViewModel.generativeModel,
                selectedCountry = advancedUiState.selectedCountry,
                genderSelection = advancedUiState.genderSelection,
                smokeSelection = advancedUiState.smokeSelection,
                socialClassSelection = advancedUiState.socialClassSelection,
                educationSelection = advancedUiState.educationSelection,
                relationShipStatus = advancedUiState.relationshipStatus,
                diabeticSelection = advancedUiState.diabeticSelection,
                jobSelection = advancedUiState.jobSelection,
                bmiSelection = advancedUiState.bmiSelection,
                exerciseSelection = advancedUiState.exerciseSelection,
                alcoholConsumption = advancedUiState.alcoholConsumption,
                dietQuality = advancedUiState.dietQuality,
                stressLevel = advancedUiState.stressLevel,
                sleepQuality = advancedUiState.sleepQuality,
                accessToHealthcare = advancedUiState.accessToHealthcare,
                environmentalConditions = advancedUiState.environmentalConditions,
                geneticPredisposition = advancedUiState.geneticPredisposition,
                modifier = Modifier.fillMaxWidth()
            ) { homeViewModel.advancedUiEvent(AdvancedUiEvent.ToggleShowAdvancedResultSheet) }
        }
    }
}