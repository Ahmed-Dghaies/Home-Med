package com.example.home_med.models

import java.util.HashMap

class m_LocalMedication {
    var m_medicationName: String? = null
    var m_medicationQty: String? = null
    var m_medicationType: String? = null
    var m_medicationExpDate: String? = null
    var m_medicationStatus: Boolean = false

    constructor() {}

    constructor(medicationName: String, medicationQty: String, medicationType: String, medicationExpDat : String, medicationStatus : Boolean) {
        this.m_medicationName = medicationName
        this.m_medicationType = medicationType
        this.m_medicationQty = medicationQty
        this.m_medicationExpDate = medicationExpDat
        this.m_medicationStatus = medicationStatus
    }

    fun toMap(): Map<String, Any> {

        val result = HashMap<String, Any>()
        result.put("medicationName", m_medicationName!!)
        result.put("medicationType", m_medicationType!!)
        result.put("medicationQty", m_medicationQty!!)
        result.put("medicationExpDate", m_medicationExpDate!!)
        result.put("medicationStatus", m_medicationStatus!!)

        return result
    }
}


