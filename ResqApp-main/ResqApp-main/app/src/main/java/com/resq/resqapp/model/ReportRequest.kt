package com.resq.resqapp.model

data class ReportRequest(
    val species: String,
    val description: String,
    val urgency: String,  // e.g., "HIGH", "MEDIUM", "LOW"
    val latitude: Double,
    val longitude: Double
)