package com.example.home_med.models

import java.util.*

data class Prescription (
    var prescription_id: Long = 0,
    var med_id: Long = 0,
    var quantity: Float = 0.0f,
    var state: Boolean
)