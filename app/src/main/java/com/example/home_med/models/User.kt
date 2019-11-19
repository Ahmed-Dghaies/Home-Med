package com.example.home_med.models

data class User (
    var user_id: Long = 0,
    var first_name: String = "",
    var Last_name: String = "",
    var email: String = "",
    var password: String = ""
)