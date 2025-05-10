package com.example.smartnutrition.data.repository

import android.content.SharedPreferences
import android.util.Log
import com.example.smartnutrition.data.model.LoginRequest
import com.example.smartnutrition.data.model.RegisterRequest
import com.example.smartnutrition.data.remote.AuthApi
import com.example.smartnutrition.data.remote.dto.LoginResponse
import com.example.smartnutrition.data.remote.dto.RegisterResponse
import com.example.smartnutrition.data.remote.dto.User
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
            .putString("auth_token", response.token)
            .apply()

        // Log respons login yang sukses
        Log.d("Login Response", response.status)
        Result.success(response)
    } catch(e: Exception) {
        Result.failure(e)
    }

    override suspend fun register(
        username: String,
        email: String,
        password: String
    ): Result<RegisterResponse> = try {
        val response = api.register(RegisterRequest(username,email, password))
        // Simpan token
        prefs.edit()
            .putString("auth_token", response.token)
            .apply()

        // Log respons login yang sukses
        Log.d("Login Response", response.status)
        Result.success(response)
    }catch (e:Exception){
        Result.failure(e)
    }

    override suspend fun verifyToken(): Result<User> {
        return try {
            val token = prefs.getString("auth_token", null)
                ?: return Result.failure(Exception("Token tidak ditemukan"))
            val response = api.verifyToken("Bearer $token")
            Result.success(response)
        }catch (e:Exception){
            Result.failure(e)
        }
    }
}