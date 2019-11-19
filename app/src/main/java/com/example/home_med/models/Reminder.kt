package com.example.home_med.models

import java.util.*

data class Reminder (
    var reminder_id: Long = 0,
    var local_med_id: Long = 0,
    var starting_day: Date = Date(),
    var ending_day: Date = Date()
)