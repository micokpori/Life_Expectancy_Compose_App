package com.rfcreations.lifeexpectancychecker.ui.home_screen.advanced

sealed class AdvancedUiEvent {
    data class UpdateSelectedCountry(val newValue: String) : AdvancedUiEvent()
    data class UpdateGenderState(val newValue: String) : AdvancedUiEvent()
    data class UpdateSmokeState(val newValue: String) : AdvancedUiEvent()
    data class UpdateSocialClassState(val newClass: String) : AdvancedUiEvent()
    data class UpdateRelationshipState(val newValue: String) : AdvancedUiEvent()
    data class UpdateEducationState(val newValue: String) : AdvancedUiEvent()
    data class UpdateBmiState(val newValue: String) : AdvancedUiEvent()
    data class UpdateJobStatus(val newValue: String) : AdvancedUiEvent()
    data class UpdateDiabeticState(val newValue: String) : AdvancedUiEvent()
    data class UpdateExerciseState(val newValue: String) : AdvancedUiEvent()
    data class UpdateAlcoholConsumption(val newValue: String) : AdvancedUiEvent()
    data class UpdateDietQuality(val newValue: String) : AdvancedUiEvent()
    data class UpdateStressLevel(val newValue: String) : AdvancedUiEvent()
    data class UpdateSleepQuality(val newValue: String) : AdvancedUiEvent()
    data class UpdateAccessToHealthcare(val newValue: String) : AdvancedUiEvent()
    data class UpdateEnvironmentalFactors(val newValue: String) : AdvancedUiEvent()
    data class UpdateGeneticPredisposition(val newValue: String) : AdvancedUiEvent()
    data object ToggleShowAdvancedResultSheet : AdvancedUiEvent()
    data object ToggleShowCountrySelectionDialog : AdvancedUiEvent()
}