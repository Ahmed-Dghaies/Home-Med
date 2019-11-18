package com.example.home_med.models

data class Medication (
    var med_id: Long = 0,
    var name: String = "",
    var description: String = "",
    var side_effects: String = ""
)