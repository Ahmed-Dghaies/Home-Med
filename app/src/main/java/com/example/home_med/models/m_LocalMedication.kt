package com.example.home_med.models

import java.util.HashMap

class m_LocalMedication {
    var m_medicationName: String? = null
    var m_medicationQty: String? = null
    var m_medicationType: String? = null
    var m_medicationExpDate: String? = null
    var m_medicationStatus: Boolean = false
    var m_medicationDays : ArrayList<String>? = null

    constructor() {}

    constructor(medicationName: String, medicationQty: String, medicationType: String, medicationExpDat : String, medicationStatus : Boolean, medicationDays : ArrayList<String>) {
        this.m_medicationName = medicationName
        this.m_medicationType = medicationType
        this.m_medicationExpDate = medicationExpDat
        this.m_medicationStatus = medicationStatus
        this.m_medicationDays = medicationDays
        this.m_medicationQty = medicationQty
    }

    fun toMap(): Map<String, Any> {

        val result = HashMap<String, Any>()
        result.put("medicationName", m_medicationName!!)
        result.put("medicationType", m_medicationType!!)
        result.put("medicationQty", m_medicationQty!!)
        result.put("medicationExpDate", m_medicationExpDate!!)
        result.put("medicationStatus", m_medicationStatus!!)
        result.put("medicationDays", m_medicationDays!!)

        return result
    }
}


