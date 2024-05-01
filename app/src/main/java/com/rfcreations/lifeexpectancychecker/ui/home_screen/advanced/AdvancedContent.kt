package com.rfcreations.lifeexpectancychecker.ui.home_screen.advanced

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.rfcreations.lifeexpectancychecker.R
import com.rfcreations.lifeexpectancychecker.ui.home_screen.HomeViewModel
import com.rfcreations.lifeexpectancychecker.ui.home_screen.components.CountryCard
import com.rfcreations.lifeexpectancychecker.ui.home_screen.components.CountrySelectionDialog
import com.rfcreations.lifeexpectancychecker.ui.home_screen.components.LifeStyleRadioGroup

@Composable
fun AdvancedContent(
    homeViewModel: HomeViewModel,
    modifier: Modifier = Modifier
) {

    val advancedUiState = homeViewModel.advancedUiState.collectAsState().value
    val showCountrySelectionDialog = homeViewModel.showAdvancedCountrySelectionDialog.collectAsState()
    val genderOptions = stringArrayResource(id = R.array.gender_options)
    val smokeOptions = stringArrayResource(id = R.array.smoking_options)
    val socialClassOptions = stringArrayResource(id = R.array.social_class_options)
    val educationOptions = stringArrayResource(id = R.array.education_options)
    val relationshipStatusOptions = stringArrayResource(id = R.array.relationship_options)
    val diabeticOptions = stringArrayResource(id = R.array.diabetic_options)
    val jobStatusOptions = stringArrayResource(id = R.array.job_status_options)
    val bmiList = stringArrayResource(id = R.array.bmi_options)
    val exerciseOptions = stringArrayResource(id = R.array.exercise_options)
    val alcoholOptions = stringArrayResource(id = R.array.alcohol_options)
    val dietQualityOptions = stringArrayResource(id = R.array.diet_quality_options)
    val stressLevelOptions = stringArrayResource(id = R.array.stress_level_options)
    val sleepQualityOptions = stringArrayResource(id = R.array.sleep_quality_options)
    val accessToHealthcareOptions = stringArrayResource(id = R.array.access_to_healthcare_options)
    val environmentalConditionsOptions = stringArrayResource(id = R.array.environmental_condition_options)
    val geneticPredispositionOptions = stringArrayResource(id = R.array.genetic_predisposition_options)

    if (showCountrySelectionDialog.value) {
        CountrySelectionDialog(
            toggleShowCountrySelectionDialog = { homeViewModel.advancedUiEvent(AdvancedUiEvent.ToggleShowCountrySelectionDialog) },
            updateSelectedCountry = { newCountry ->
                homeViewModel.advancedUiEvent(
                    AdvancedUiEvent.UpdateSelectedCountry(newCountry)
                )
            },
            dismissRequest = {
                homeViewModel.advancedUiEvent(AdvancedUiEvent.ToggleShowCountrySelectionDialog)
            }
        )
    }

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(
            space = 8.dp,
            alignment = Alignment.CenterVertically
        )
    ) {
        item {
            CountryCard(
                modifier = modifier,
                selectedCountry = advancedUiState.selectedCountry
            ) {
                homeViewModel.advancedUiEvent(AdvancedUiEvent.ToggleShowCountrySelectionDialog)
            }
            LifeStyleRadioGroup(
                title = stringResource(id = R.string.are_you_male_or_female),
                imageDrawable = R.drawable.gender,
                radioButtonOptions = genderOptions,
                selectedOption = advancedUiState.genderSelection,
                modifier = modifier
            ) { newGender ->
                homeViewModel.advancedUiEvent(AdvancedUiEvent.UpdateGenderState(newGender))
            }

            LifeStyleRadioGroup(
                title = stringResource(id = R.string.do_you_smoke),
                imageDrawable = R.drawable.smoke,
                radioButtonOptions = smokeOptions,
                selectedOption = advancedUiState.smokeSelection,
                modifier = modifier
            ) { newSmokeValue ->
                homeViewModel.advancedUiEvent(AdvancedUiEvent.UpdateSmokeState(newSmokeValue))
            }

            LifeStyleRadioGroup(
                title = stringResource(id = R.string.social_class),
                imageDrawable = R.drawable.class_image,
                radioButtonOptions = socialClassOptions,
                selectedOption = advancedUiState.socialClassSelection,
                modifier = modifier
            ) { newSocialClass ->
                homeViewModel.advancedUiEvent(AdvancedUiEvent.UpdateSocialClassState(newSocialClass))
            }

            LifeStyleRadioGroup(
                title = stringResource(id = R.string.what_is_your_education_level),
                imageDrawable = R.drawable.school,
                radioButtonOptions = educationOptions,
                selectedOption = advancedUiState.educationSelection,
                modifier = modifier
            ) { newEdu ->
                homeViewModel.advancedUiEvent(AdvancedUiEvent.UpdateEducationState(newEdu))
            }

            LifeStyleRadioGroup(
                title = stringResource(id = R.string.relationship_status),
                imageDrawable = R.drawable.love,
                radioButtonOptions = relationshipStatusOptions,
                selectedOption = advancedUiState.relationshipStatus,
                modifier = modifier
            ) { newRelationShipValue ->
                homeViewModel.advancedUiEvent(AdvancedUiEvent.UpdateRelationshipState(newRelationShipValue))
            }

            LifeStyleRadioGroup(
                title = stringResource(id = R.string.are_you_diabetic),
                imageDrawable = R.drawable.virus,
                radioButtonOptions = diabeticOptions,
                selectedOption = advancedUiState.diabeticSelection,
                modifier = modifier
            ) { newDiabeticValue ->
                homeViewModel.advancedUiEvent(AdvancedUiEvent.UpdateDiabeticState(newDiabeticValue))
            }

            LifeStyleRadioGroup(
                title = stringResource(id = R.string.do_you_have_a_job),
                imageDrawable = R.drawable.job,
                radioButtonOptions = jobStatusOptions,
                selectedOption = advancedUiState.jobSelection,
                modifier = modifier
            ) { newJobValue ->
                homeViewModel.advancedUiEvent(AdvancedUiEvent.UpdateJobStatus(newJobValue))
            }

            LifeStyleRadioGroup(
                title = stringResource(id = R.string.whats_your_bmi),
                imageDrawable = R.drawable.bmi,
                radioButtonOptions = bmiList,
                selectedOption = advancedUiState.bmiSelection,
                modifier = modifier
            ) { newBmi ->
                homeViewModel.advancedUiEvent(AdvancedUiEvent.UpdateBmiState(newBmi))
            }

            LifeStyleRadioGroup(
                title = stringResource(id = R.string.what_s_your_exercise_level),
                imageDrawable = R.drawable.exercise,
                radioButtonOptions = exerciseOptions,
                selectedOption = advancedUiState.exerciseSelection,
                modifier = modifier
            ) { newExerciseValue ->
                homeViewModel.advancedUiEvent(AdvancedUiEvent.UpdateExerciseState(newExerciseValue))
            }

            LifeStyleRadioGroup(
                title = stringResource(id = R.string.alcohol_consumption),
                imageDrawable = R.drawable.wine_bar_24,
                radioButtonOptions = alcoholOptions,
                selectedOption = advancedUiState.alcoholConsumption,
                modifier = modifier
            ) { newValue ->
                homeViewModel.advancedUiEvent(AdvancedUiEvent.UpdateAlcoholConsumption(newValue))
            }

            LifeStyleRadioGroup(
                title = stringResource(id = R.string.diet_quality),
                imageDrawable = R.drawable.fastfood_24,
                radioButtonOptions = dietQualityOptions,
                selectedOption = advancedUiState.dietQuality,
                modifier = modifier
            ) { newDietValue ->
                homeViewModel.advancedUiEvent(AdvancedUiEvent.UpdateDietQuality(newDietValue))
            }

            LifeStyleRadioGroup(
                title = stringResource(id = R.string.stress_level),
                imageDrawable = R.drawable.headache_icon,
                radioButtonOptions = stressLevelOptions,
                selectedOption = advancedUiState.stressLevel,
                modifier = modifier
            ) { newValue ->
                homeViewModel.advancedUiEvent(AdvancedUiEvent.UpdateStressLevel(newValue))
            }

            LifeStyleRadioGroup(
                title = stringResource(id = R.string.sleep_quality),
                imageDrawable = R.drawable.bed_24,
                radioButtonOptions = sleepQualityOptions,
                selectedOption = advancedUiState.sleepQuality,
                modifier = modifier
            ) { newValue ->
                homeViewModel.advancedUiEvent(AdvancedUiEvent.UpdateSleepQuality(newValue))
            }

            LifeStyleRadioGroup(
                title = stringResource(id = R.string.access_to_healthcare),
                imageDrawable = R.drawable.medical_services_24,
                radioButtonOptions = accessToHealthcareOptions,
                selectedOption = advancedUiState.accessToHealthcare,
                modifier = modifier
            ) { newValue ->
                homeViewModel.advancedUiEvent(AdvancedUiEvent.UpdateAccessToHealthcare(newValue))
            }

            LifeStyleRadioGroup(
                title = stringResource(id = R.string.environmental_conditions),
                imageDrawable = R.drawable.landscape_24,
                radioButtonOptions = environmentalConditionsOptions,
                selectedOption = advancedUiState.environmentalConditions,
                modifier = modifier
            ) { newValue ->
                homeViewModel.advancedUiEvent(AdvancedUiEvent.UpdateEnvironmentalFactors(newValue))
            }

            LifeStyleRadioGroup(
                title = stringResource(id = R.string.geneticpredisposition),
                imageDrawable = R.drawable.dna_icon,
                radioButtonOptions = geneticPredispositionOptions,
                selectedOption = advancedUiState.geneticPredisposition,
                modifier = modifier
            ) { newValue ->
                homeViewModel.advancedUiEvent(AdvancedUiEvent.UpdateGeneticPredisposition(newValue))
            }
        }
    }
}