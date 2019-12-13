package com.example.home_med.models

/**
 * Medication data class for ID, name, description and side effects
 *
 * @property med_id Medication ID
 * @property name Name of the medication
 * @property description Description of the medication
 * @property side_effects Side effects of the medication
 */
data class Medication (
    var med_id: Long = 0,
    var name: String = "",
    var description: String = "",
    var side_effects: String = ""
)