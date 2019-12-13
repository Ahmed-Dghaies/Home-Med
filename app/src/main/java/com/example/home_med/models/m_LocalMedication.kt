package com.example.home_med.models

import java.util.HashMap

/**
 * Local Medication Database Class
 * Stores the variables from the database into separated variables for easy access
 *
 * @constructor Empty constructor with data slots for the local medication information
 *
 * @property m_medicationName Name of the medication
 * @property m_medicationQty Medication quantity
 * @property m_medicationType Type of the medication
 * @property m_medicationExpDate Medication expiration date
 * @property m_userID User identification number
 * @property m_medicationStatus Medication status of active or inactive
 * @property m_medicationDays Days medication is used / active
 */
class m_LocalMedication {
    var m_medicationName: String? = null
    var m_medicationQty: String? = null
    var m_medicationType: String? = null
    var m_medicationExpDate: String? = null
    var m_userID: String? = null
    var m_medicationStatus: Boolean = false
    var m_medicationDays : ArrayList<String>? = null

    constructor() {}

    /**
     * Empty constructor with data slots for the local medication information
     *
     * @param medicationName Name of the medication as a String
     * @param medicationQty Quantity of the medication as a String
     * @param medicationType Type of the medication as a String
     * @param medicationExpDat Expiration data of the medication as a String
     * @param medicationStatus Whether the medication is active or inactive
     * @param medicationDays List of days that the medication is active
     * @param userID ID of the user as a String
     */
    constructor(medicationName: String, medicationQty: String, medicationType: String, medicationExpDat : String, medicationStatus : Boolean, medicationDays : ArrayList<String>, userID : String) {
        this.m_medicationName = medicationName
        this.m_medicationType = medicationType
        this.m_medicationExpDate = medicationExpDat
        this.m_medicationStatus = medicationStatus
        this.m_medicationDays = medicationDays
        this.m_medicationQty = medicationQty
        this.m_userID = userID
    }

    /**
     * Maps the data given from the constructor to the medication name, type, qty, expDate, status, days, and user ID
     *
     * @return Returns the result of the mapping
     */
    fun toMap(): Map<String, Any> {

        val result = HashMap<String, Any>()
        result.put("medicationName", m_medicationName!!)
        result.put("medicationType", m_medicationType!!)
        result.put("medicationQty", m_medicationQty!!)
        result.put("medicationExpDate", m_medicationExpDate!!)
        result.put("medicationStatus", m_medicationStatus!!)
        result.put("medicationDays", m_medicationDays!!)
        result.put("userID", m_userID!!)

        return result
    }
}


