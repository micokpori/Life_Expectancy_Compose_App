package com.rfcreations.lifeexpectancychecker.ui.home_screen

import androidx.lifecycle.ViewModel
import com.google.ai.client.generativeai.GenerativeModel
import com.rfcreations.lifeexpectancychecker.ui.home_screen.advanced.AdvancedUiEvent
import com.rfcreations.lifeexpectancychecker.ui.home_screen.advanced.AdvancedUiState
import com.rfcreations.lifeexpectancychecker.ui.home_screen.basic.BasicUiEvent
import com.rfcreations.lifeexpectancychecker.ui.home_screen.basic.BasicUiState
import com.rfcreations.lifeexpectancychecker.ui.theme.ThemeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val themeUiState: ThemeUiState,
    val generativeModel: GenerativeModel
) : ViewModel() {

    private val _basicUiState = MutableStateFlow(BasicUiState())
    val basicUiState = _basicUiState.asStateFlow()

    private val _advancedUiState = MutableStateFlow(AdvancedUiState())
    val advancedUiState = _advancedUiState.asStateFlow()

    private val _showAppThemeDialog = MutableStateFlow(false)
    val showAppThemeDialog = _showAppThemeDialog.asStateFlow()

    private val _showBasicResultSheet = MutableStateFlow(false)
    val showBasicResultSheet = _showBasicResultSheet.asStateFlow()

    private val _showAdvancedResultSheet = MutableStateFlow(false)
    val showAdvancedResultSheet = _showAdvancedResultSheet.asStateFlow()

    private val _showBasicCountrySelectionDialog = MutableStateFlow(false)
    val showBasicCountrySelectionDialog = _showBasicCountrySelectionDialog.asStateFlow()

    private val _showAdvancedCountrySelectionDialog = MutableStateFlow(false)
    val showAdvancedCountrySelectionDialog = _showAdvancedCountrySelectionDialog.asStateFlow()

    fun toggleShowAppThemeDialog() {
        _showAppThemeDialog.value = !_showAppThemeDialog.value
    }

    fun advancedUiEvent(event: AdvancedUiEvent) {
        when (event) {
            is AdvancedUiEvent.UpdateSelectedCountry -> {
                _advancedUiState.value =
                    _advancedUiState.value.copy(selectedCountry = event.newValue)
            }

            is AdvancedUiEvent.UpdateGenderState -> {
                _advancedUiState.value =
                    _advancedUiState.value.copy(genderSelection = event.newValue)
            }

            is AdvancedUiEvent.UpdateSmokeState -> {
                _advancedUiState.value =
                    _advancedUiState.value.copy(smokeSelection = event.newValue)
            }

            is AdvancedUiEvent.UpdateSocialClassState -> {
                _advancedUiState.value =
                    _advancedUiState.value.copy(socialClassSelection = event.newClass)
            }

            is AdvancedUiEvent.UpdateRelationshipState -> {
                _advancedUiState.value =
                    _advancedUiState.value.copy(relationshipStatus = event.newValue)
            }

            is AdvancedUiEvent.UpdateEducationState -> {
                _advancedUiState.value =
                    _advancedUiState.value.copy(educationSelection = event.newValue)
            }

            is AdvancedUiEvent.UpdateBmiState -> {
                _advancedUiState.value = _advancedUiState.value.copy(bmiSelection = event.newValue)
            }

            is AdvancedUiEvent.UpdateJobStatus -> {
                _advancedUiState.value = _advancedUiState.value.copy(jobSelection = event.newValue)
            }

            is AdvancedUiEvent.UpdateDiabeticState -> {
                _advancedUiState.value =
                    _advancedUiState.value.copy(diabeticSelection = event.newValue)
            }

            is AdvancedUiEvent.UpdateExerciseState -> {
                _advancedUiState.value =
                    _advancedUiState.value.copy(exerciseSelection = event.newValue)
            }

            is AdvancedUiEvent.UpdateAlcoholConsumption -> {
                _advancedUiState.value =
                    _advancedUiState.value.copy(alcoholConsumption = event.newValue)
            }

            is AdvancedUiEvent.UpdateDietQuality -> {
                _advancedUiState.value = _advancedUiState.value.copy(dietQuality = event.newValue)
            }

            is AdvancedUiEvent.UpdateStressLevel -> {
                _advancedUiState.value = _advancedUiState.value.copy(stressLevel = event.newValue)
            }

            is AdvancedUiEvent.UpdateSleepQuality -> {
                _advancedUiState.value = _advancedUiState.value.copy(sleepQuality = event.newValue)
            }

            is AdvancedUiEvent.UpdateAccessToHealthcare -> {
                _advancedUiState.value =
                    _advancedUiState.value.copy(accessToHealthcare = event.newValue)
            }

            is AdvancedUiEvent.UpdateEnvironmentalFactors -> {
                _advancedUiState.value =
                    _advancedUiState.value.copy(environmentalConditions = event.newValue)
            }

            is AdvancedUiEvent.UpdateGeneticPredisposition -> {
                _advancedUiState.value =
                    _advancedUiState.value.copy(geneticPredisposition = event.newValue)
            }

            is AdvancedUiEvent.ToggleShowCountrySelectionDialog -> {
                _showAdvancedCountrySelectionDialog.value =
                    !_showAdvancedCountrySelectionDialog.value
            }

            AdvancedUiEvent.ToggleShowAdvancedResultSheet -> {
                _showAdvancedResultSheet.value = !_showAdvancedResultSheet.value
            }
        }
    }

    fun basicUiEvent(event: BasicUiEvent) {
        when (event) {
            is BasicUiEvent.UpdateSelectedCountry -> {
                _basicUiState.update {
                    it.copy(selectedCountry = event.newValue)
                }
            }

            is BasicUiEvent.UpdateGenderState -> {
                _basicUiState.update {
                    it.copy(genderSelection = event.newValue)
                }
            }

            is BasicUiEvent.UpdateSmokeState -> {
                _basicUiState.update {
                    it.copy(smokeSelection = event.newValue)
                }
            }

            is BasicUiEvent.UpdateSocialClassState -> {
                _basicUiState.update {
                    it.copy(socialClassSelection = event.newClass)
                }
            }

            is BasicUiEvent.UpdateRelationshipState -> {
                _basicUiState.update {
                    it.copy(relationShipStatus = event.newValue)
                }
            }

            is BasicUiEvent.UpdateEducationState -> {
                _basicUiState.update {
                    it.copy(educationSelection = event.newValue)
                }
            }

            is BasicUiEvent.UpdateBmiState -> {
                _basicUiState.update {
                    it.copy(bmiSelection = event.newValue)
                }
            }

            is BasicUiEvent.UpdateJobStatus -> {
                _basicUiState.update {
                    it.copy(jobSelection = event.newValue)
                }
            }

            is BasicUiEvent.UpdateDiabeticState -> {
                _basicUiState.update {
                    it.copy(diabeticSelection = event.newValue)
                }
            }

            is BasicUiEvent.UpdateExerciseState -> {
                _basicUiState.update {
                    it.copy(exerciseSelection = event.newValue)
                }
            }

            is BasicUiEvent.ToggleShowResultSheet -> {
                _showBasicResultSheet.value = !_showBasicResultSheet.value
            }

            is BasicUiEvent.ToggleShowCountrySelectionDialog -> {
                _showBasicCountrySelectionDialog.value = !_showBasicCountrySelectionDialog.value
            }
        }
    }

    fun resetUiSelections() {
        _basicUiState.value = BasicUiState()
        _advancedUiState.value = AdvancedUiState()
    }

    //check if all radio groups or a radio group is unselected
    //if true show a toast that asks the user to select all options
    fun shouldShowBasicReminderToast(): Boolean {
        return _basicUiState.value.selectedCountry.isEmpty() ||
                _basicUiState.value.genderSelection.isEmpty() ||
                _basicUiState.value.smokeSelection.isEmpty() ||
                _basicUiState.value.socialClassSelection.isEmpty() ||
                _basicUiState.value.educationSelection.isEmpty() ||
                _basicUiState.value.relationShipStatus.isEmpty() ||
                _basicUiState.value.diabeticSelection.isEmpty() ||
                _basicUiState.value.jobSelection.isEmpty() ||
                _basicUiState.value.bmiSelection.isEmpty() ||
                _basicUiState.value.exerciseSelection.isEmpty()
    }

    fun shouldShowAdvancedReminderToast(): Boolean {
        return _advancedUiState.value.selectedCountry.isEmpty() ||
                _advancedUiState.value.genderSelection.isEmpty() ||
                _advancedUiState.value.smokeSelection.isEmpty() ||
                _advancedUiState.value.socialClassSelection.isEmpty() ||
                _advancedUiState.value.educationSelection.isEmpty() ||
                _advancedUiState.value.relationshipStatus.isEmpty() ||
                _advancedUiState.value.diabeticSelection.isEmpty() ||
                _advancedUiState.value.jobSelection.isEmpty() ||
                _advancedUiState.value.bmiSelection.isEmpty() ||
                _advancedUiState.value.exerciseSelection.isEmpty() ||
                _advancedUiState.value.alcoholConsumption.isEmpty() ||
                _advancedUiState.value.dietQuality.isEmpty() ||
                _advancedUiState.value.stressLevel.isEmpty() ||
                _advancedUiState.value.sleepQuality.isEmpty() ||
                _advancedUiState.value.accessToHealthcare.isEmpty() ||
                _advancedUiState.value.environmentalConditions.isEmpty() ||
                _advancedUiState.value.geneticPredisposition.isEmpty()
    }

}