package com.rfcreations.lifeexpectancychecker.ui.home_screen.basic

sealed class BasicUiEvent {
    data class UpdateSelectedCountry(val newValue: String) : BasicUiEvent()
    data class UpdateGenderState(val newValue: String) : BasicUiEvent()
    data class UpdateSmokeState(val newValue: String) : BasicUiEvent()
    data class UpdateSocialClassState(val newClass: String) : BasicUiEvent()
    data class UpdateRelationshipState(val newValue: String) : BasicUiEvent()
    data class UpdateEducationState(val newValue: String) : BasicUiEvent()
    data class UpdateBmiState(val newValue: String) : BasicUiEvent()
    data class UpdateJobStatus(val newValue: String) : BasicUiEvent()
    data class UpdateDiabeticState(val newValue: String) : BasicUiEvent()
    data class UpdateExerciseState(val newValue: String) : BasicUiEvent()
    data object ToggleShowResultSheet : BasicUiEvent()
    data object ToggleShowCountrySelectionDialog : BasicUiEvent()
}