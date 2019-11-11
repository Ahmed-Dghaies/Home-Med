package com.example.home_med.models

import java.util.*

data class Prescription (
    var prescription_id: Long = 0,
    var local_med_id: Long = 0,
    var quantity: Float = 0.0f,
    var expiration_date: Date = Date()
)