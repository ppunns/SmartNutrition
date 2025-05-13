package com.example.smartnutrition.domain.repository

import com.example.smartnutrition.data.remote.dto.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun getUser(): Flow<User>
    suspend fun saveUser(user: User)
    suspend fun logout()
}