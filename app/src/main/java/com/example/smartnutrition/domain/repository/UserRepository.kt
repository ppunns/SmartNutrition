package com.example.smartnutrition.domain.repository


import android.net.Uri
import com.example.smartnutrition.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUser(): Flow<User>
    suspend fun updateUser(user: User)
    suspend fun updateProfilePhoto(photoUri: Uri): String
    suspend fun updateProteinTarget(proteinTarget: Int)
    suspend fun logout()
}