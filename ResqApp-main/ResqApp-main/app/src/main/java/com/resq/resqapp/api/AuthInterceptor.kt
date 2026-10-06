package com.resq.resqapp.api

import com.resq.resqapp.util.PrefUtils
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val context: android.content.Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val token = PrefUtils.getToken(context)
        val authorizedRequest = if (token != null) {
            originalRequest.newBuilder()
                .header("Authorization", "Bearer $token")
                .build()
        } else {
            originalRequest
        }
        return chain.proceed(authorizedRequest)
    }
}