package com.resq.resqapp.util

import android.content.Context
import android.content.SharedPreferences

object PrefUtils {
    private const val PREF_NAME = "resq_prefs"
    private const val KEY_TOKEN = "jwt_token"
    private const val KEY_ROLE = "user_role"
    private const val KEY_USER_ID = "user_id"  // New key for userId

    private fun getPrefs(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    // Updated saveToken to accept userId
    fun saveToken(context: Context, token: String, role: String, userId: Long) {
        getPrefs(context).edit().apply {
            putString(KEY_TOKEN, token)
            putString(KEY_ROLE, role)
            putLong(KEY_USER_ID, userId)
            apply()
        }
    }

    fun getToken(context: Context): String? {
        return getPrefs(context).getString(KEY_TOKEN, null)
    }

    fun getRole(context: Context): String? {
        return getPrefs(context).getString(KEY_ROLE, null)
    }

    // New getter for userId
    fun getUserId(context: Context): Long {
        return getPrefs(context).getLong(KEY_USER_ID, -1L)  // Default -1 if not found
    }

    fun clear(context: Context) {
        getPrefs(context).edit().clear().apply()
    }
}