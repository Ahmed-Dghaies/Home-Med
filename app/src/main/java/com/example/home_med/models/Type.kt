package com.example.home_med.models

/**
 * Type class for medication types and ID's
 *
 * @property type_id Type of medication ID
 * @property specification Specification on which type of medication is stored
 */
data class Type(
    var type_id: Long = 0,
    var specification: String = ""
)