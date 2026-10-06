package com.resq.resqapp.api

import com.resq.resqapp.model.LoginRequest
import com.resq.resqapp.model.LoginResponse
import com.resq.resqapp.model.NgoRegisterRequest
import com.resq.resqapp.model.ReportRequest
import com.resq.resqapp.model.ReportResponse
import com.resq.resqapp.model.UserRegisterRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    // Existing auth
    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @POST("auth/register/user")
    suspend fun registerUser(@Body request: UserRegisterRequest): Response<String>

    @POST("auth/register/ngo")
    suspend fun registerNgo(@Body request: NgoRegisterRequest): Response<String>

    // New report endpoints
    @POST("api/reports")
    suspend fun createReport(@Body request: ReportRequest): Response<ReportResponse>

    @GET("api/reports/{id}")
    suspend fun getReport(@Path("id") id: Long): Response<ReportResponse>

    @GET("api/reports/user/{userId}")
    suspend fun getUserReports(@Path("userId") userId: Long): Response<List<ReportResponse>>

    @GET("api/ngos/assignments")
    suspend fun getNgoAssignments(): Response<List<ReportResponse>>
}