package com.example.home_med.models

import java.util.HashMap

/**
 * User class to store user information
 *
 * @constructor Empty constructor to be filled with user information
 *
 * @property user_id ID of the user
 * @property first_name First name of the user
 * @property last_name Last name of the user
 * @property age Age of the user
 * @property email Email address that is associated with the user
 * @property password Password that has been created associated with user email address
 */
class User {
    var user_id: String = ""
    var first_name: String = ""
    var last_name: String = ""
    var age: Int = 0
    var email: String = ""
    var password: String = ""

    constructor() {}

    /**
     * Empty constructor to be filled with user ID, first name, last name, age, and email address
     *
     * @param user_id ID of the user
     * @param first_name First name of the user
     * @param last_name Last name of the user
     * @param age Age of the user
     * @param email Email address that is associated with the user
     */
    constructor(user_id: String, first_name: String, last_name: String, age: Int, email: String) {
        this.user_id = user_id
        this.first_name = first_name
        this.last_name = last_name
        this.age = age
        this.email = email
    }

    /**
     * Maps the user information from the database
     *
     * @return Returns the result of the user information
     */
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