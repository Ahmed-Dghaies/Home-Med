package com.example.home_med.models

import java.util.*

/**
 * Prescription class for the medication
 *
 * @property prescription_id ID of the prescription
 * @property med_id Medication ID
 * @property quantity Quantity of the medication
 * @property state State of the medication (Active / Inactive)
 */
data class Prescription (
    var prescription_id: Long = 0,
    var med_id: Long = 0,
    var quantity: Float = 0.0f,
    var state: Boolean
)