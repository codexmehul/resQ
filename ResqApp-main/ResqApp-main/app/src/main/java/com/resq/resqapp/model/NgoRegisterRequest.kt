package com.resq.resqapp.model

data class NgoRegisterRequest(
    val email: String,
    val password: String,
    val name: String,
    val specialization: String,
    val latitude: Double,
    val longitude: Double
)