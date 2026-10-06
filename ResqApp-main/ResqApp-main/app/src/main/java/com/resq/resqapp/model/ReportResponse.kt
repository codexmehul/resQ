package com.resq.resqapp.model

data class ReportResponse(
    val id: Long,
    val userId: Long,
    val species: String,
    val description: String,
    val urgency: String,
    val latitude: Double,
    val longitude: Double,
    val reportedAt: String,
    val status: String,
    val assignedNgoId: Long?
)