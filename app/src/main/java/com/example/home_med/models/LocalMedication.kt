package com.example.home_med.models

data class LocalMedication (
    var local_med_id: Long = 0,
    var prescription_id: Long = 0,
    var type_id: Long = 0,
    var user_id: Long = 0,
    var quantity: Int = 0
)