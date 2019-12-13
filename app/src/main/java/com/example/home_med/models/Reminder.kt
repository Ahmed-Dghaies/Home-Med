package com.example.home_med.models

import java.util.*

/**
 * Reminder class for the medication for notifications
 *
 * @property reminder_id ID for the reminder of each medication
 * @property local_med_id Local medication ID
 * @property starting_day Which day to start taking the medication
 * @property ending_day Which day to stop taking the medication
 */
data class Reminder (
    var reminder_id: Long = 0,
    var local_med_id: Long = 0,
    var starting_day: Date = Date(),
    var ending_day: Date = Date()
)