package com.example.smartnutrition.data.manager

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

class TokenManager @Inject constructor(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("SmartNutrition", Context.MODE_PRIVATE)
    private val editor = prefs.edit()

    companion object {
        const val USER_TOKEN = ""
    }

    fun saveToken(token: String) {
        editor.putString(USER_TOKEN, token)
        editor.apply()
    }

    fun getToken(): String? {
        return prefs.getString(USER_TOKEN, null)
    }

    fun deleteToken() {
        editor.remove(USER_TOKEN)
        editor.apply()
    }
}