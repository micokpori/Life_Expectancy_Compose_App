package com.rfcreations.lifeexpectancychecker.ui.home_screen.basic

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
import com.rfcreations.lifeexpectancychecker.ui.home_screen.components.LifeStyleRadioGroup
import com.rfcreations.lifeexpectancychecker.ui.home_screen.components.CountryCard
import com.rfcreations.lifeexpectancychecker.ui.home_screen.components.CountrySelectionDialog

@Composable
fun BasicContent(
    homeViewModel: HomeViewModel,
    modifier: Modifier = Modifier
) {
    val basicUiState = homeViewModel.basicUiState.collectAsState().value
    val showCountrySelectionDialog = homeViewModel.showBasicCountrySelectionDialog.collectAsState()
    val genderOptions = stringArrayResource(id = R.array.gender_options)
    val smokeOptions = stringArrayResource(id = R.array.smoking_options)
    val socialClassOptions = stringArrayResource(id = R.array.social_class_options)
    val educationOptions = stringArrayResource(id = R.array.education_options)
    val relationshipStatusOptions = stringArrayResource(id = R.array.relationship_options)
    val diabeticOptions = stringArrayResource(id = R.array.diabetic_options)
    val jobStatusOptions = stringArrayResource(id = R.array.job_status_options)
    val bmiList = stringArrayResource(id = R.array.bmi_options)
    val exerciseOptions = stringArrayResource(id = R.array.exercise_options)
    if (showCountrySelectionDialog.value) {
        CountrySelectionDialog(
            toggleShowCountrySelectionDialog = { homeViewModel.basicUiEvent(BasicUiEvent.ToggleShowCountrySelectionDialog) },
            updateSelectedCountry = { newCountry ->
                homeViewModel.basicUiEvent(
                    BasicUiEvent.UpdateSelectedCountry(newCountry)
                )
            },
            dismissRequest = {
                homeViewModel.basicUiEvent(BasicUiEvent.ToggleShowCountrySelectionDialog)
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
                selectedCountry = basicUiState.selectedCountry
            ) {
                homeViewModel.basicUiEvent(BasicUiEvent.ToggleShowCountrySelectionDialog)
            }

            LifeStyleRadioGroup(
                title = stringResource(id = R.string.are_you_male_or_female),
                imageDrawable = R.drawable.gender,
                radioButtonOptions = genderOptions,
                selectedOption = basicUiState.genderSelection,
                modifier = modifier
            ) { newGender ->
                homeViewModel.basicUiEvent(BasicUiEvent.UpdateGenderState(newGender))
            }

            LifeStyleRadioGroup(
                title = stringResource(id = R.string.do_you_smoke),
                imageDrawable = R.drawable.smoke,
                radioButtonOptions = smokeOptions,
                selectedOption = basicUiState.smokeSelection,
                modifier = modifier
            ) { newSmokeValue ->
                homeViewModel.basicUiEvent(BasicUiEvent.UpdateSmokeState(newSmokeValue))
            }

            LifeStyleRadioGroup(
                title = stringResource(id = R.string.social_class),
                imageDrawable = R.drawable.class_image,
                radioButtonOptions = socialClassOptions,
                selectedOption = basicUiState.socialClassSelection,
                modifier = modifier
            ) { newSocialClass ->
                homeViewModel.basicUiEvent(BasicUiEvent.UpdateSocialClassState(newSocialClass))
            }

            LifeStyleRadioGroup(
                title = stringResource(id = R.string.what_is_your_education_level),
                imageDrawable = R.drawable.school,
                radioButtonOptions = educationOptions,
                selectedOption = basicUiState.educationSelection,
                modifier = modifier
            ) { newEdu ->
                homeViewModel.basicUiEvent(BasicUiEvent.UpdateEducationState(newEdu))
            }

            LifeStyleRadioGroup(
                title = stringResource(id = R.string.relationship_status),
                imageDrawable = R.drawable.love,
                radioButtonOptions = relationshipStatusOptions,
                selectedOption = basicUiState.relationShipStatus,
                modifier = modifier
            ) { newRelationShipValue ->
                homeViewModel.basicUiEvent(BasicUiEvent.UpdateRelationshipState(newRelationShipValue))
            }

            LifeStyleRadioGroup(
                title = stringResource(id = R.string.are_you_diabetic),
                imageDrawable = R.drawable.virus,
                radioButtonOptions = diabeticOptions,
                selectedOption = basicUiState.diabeticSelection,
                modifier = modifier
            ) { newDiabeticValue ->
                homeViewModel.basicUiEvent(BasicUiEvent.UpdateDiabeticState(newDiabeticValue))
            }

            LifeStyleRadioGroup(
                title = stringResource(id = R.string.do_you_have_a_job),
                imageDrawable = R.drawable.job,
                radioButtonOptions = jobStatusOptions,
                selectedOption = basicUiState.jobSelection,
                modifier = modifier
            ) { newJobValue ->
                homeViewModel.basicUiEvent(BasicUiEvent.UpdateJobStatus(newJobValue))
            }

            LifeStyleRadioGroup(
                title = stringResource(id = R.string.whats_your_bmi),
                imageDrawable = R.drawable.bmi,
                radioButtonOptions = bmiList,
                selectedOption = basicUiState.bmiSelection,
                modifier = modifier
            ) { newBmi ->
                homeViewModel.basicUiEvent(BasicUiEvent.UpdateBmiState(newBmi))
            }

            LifeStyleRadioGroup(
                title = stringResource(id = R.string.what_s_your_exercise_level),
                imageDrawable = R.drawable.exercise,
                radioButtonOptions = exerciseOptions,
                selectedOption = basicUiState.exerciseSelection,
                modifier = modifier
            ) { newExerciseValue ->
                homeViewModel.basicUiEvent(BasicUiEvent.UpdateExerciseState(newExerciseValue))
            }
        }
    }
}