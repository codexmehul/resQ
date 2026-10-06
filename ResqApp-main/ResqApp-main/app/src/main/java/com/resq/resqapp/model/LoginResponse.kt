package com.resq.resqapp.model

data class LoginResponse(
    val token: String,
    val userId: Long  // Now included from backend
)