package com.example.smartnutrition.data.manager

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TokenManager @Inject constructor(@ApplicationContext context: Context) {
    private var prefs: SharedPreferences =
        context.getSharedPreferences("smart_nutrition_prefs", Context.MODE_PRIVATE)
    private var editor = prefs.edit()

    companion object {
        const val USER_TOKEN = "user_token"
        const val USER_ID = "user_id"  // Menambahkan konstanta untuk user ID
    }

    fun saveToken(token: String) {
        editor.putString(USER_TOKEN, token)
        editor.apply()
    }

    fun getToken(): String? {
        return prefs.getString(USER_TOKEN, null)
    }

    // Menambahkan fungsi untuk menyimpan user ID
    fun saveUserId(userId: String) {
        editor.putString(USER_ID, userId)
        editor.apply()
    }

    // Menambahkan fungsi untuk mengambil user ID
    fun getUserId(): String? {
        return prefs.getString(USER_ID, null)
    }

    fun deleteToken() {
        editor.remove(USER_TOKEN)
        editor.remove(USER_ID)  // Menghapus user ID saat logout
        editor.apply()
    }
}