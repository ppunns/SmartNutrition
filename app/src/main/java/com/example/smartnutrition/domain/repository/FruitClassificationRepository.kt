package com.example.smartnutrition.domain.repository

import android.graphics.Bitmap
import com.example.smartnutrition.data.model.FruitClassificationResult
import kotlinx.coroutines.flow.Flow


interface FruitClassificationRepository {
    suspend fun classifyImage(bitmap: Bitmap): Flow<Result<FruitClassificationResult>>
}