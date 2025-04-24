package com.example.smartnutrition.data.repository

import android.content.SharedPreferences
import android.util.Log
import com.example.smartnutrition.data.model.LoginRequest
import com.example.smartnutrition.data.remote.AuthApi
import com.example.smartnutrition.data.remote.dto.LoginResponse
import com.example.smartnutrition.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val api: AuthApi,
    private val prefs: SharedPreferences
) : AuthRepository {

    override suspend fun login(email: String, password: String): Result<LoginResponse> = try {
        val response = api.login(LoginRequest(email, password))
        // Simpan token
        prefs.edit()
            .putString("auth_token", response.data.token)
            .apply()

        // Log respons login yang sukses
        Log.d("Login Response", response.message)
        Result.success(response)
    } catch(e: Exception) {
        Result.failure(e)
    }
}