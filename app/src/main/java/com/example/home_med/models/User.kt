package com.example.home_med.models

import java.util.HashMap

class User {
    var user_id: String = ""
    var first_name: String = ""
    var last_name: String = ""
    var age: Int = 0
    var email: String = ""
    var password: String = ""

    constructor() {}

    constructor(user_id: String, first_name: String, last_name: String, age: Int, email: String) {
        this.user_id = user_id
        this.first_name = first_name
        this.last_name = last_name
        this.age = age
        this.email = email
    }

    fun toMap(): Map<String, Any> {

        val result = HashMap<String, Any>()
        result.put("userFirstName", first_name)
        result.put("userLastName", last_name)
        result.put("userAge", age)
        result.put("userEmail", email)
        result.put("userPassword", password)

        return result
    }
}