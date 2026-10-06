package com.resq.resqapp.model

data class LoginRequest(
    val email: String,
    val password: String,
    val role: String  // "USER" or "NGO"
)