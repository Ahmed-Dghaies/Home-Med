package com.example.home_med.models

/**
 * Time class to determine which time to take each medication for notifications
 *
 * @property time_id ID of each time to take medications
 * @property reminder_id ID for each reminder
 * @property hours Which hour to take the medication
 * @property minutes Which minute to take the medication
 */
data class Time (
    var time_id: Long = 0,
    var reminder_id: Long = 0,
    var hours: Int = 0,
    var minutes: Int = 0
)