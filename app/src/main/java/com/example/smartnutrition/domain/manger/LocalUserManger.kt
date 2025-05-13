package com.example.smartnutrition.domain.manger

import android.net.Uri
import com.example.smartnutrition.data.remote.dto.User
import kotlinx.coroutines.flow.Flow

interface LocalUserManger {
    suspend fun saveAppEntry()
    fun readAppEntry(): Flow<Boolean>
}