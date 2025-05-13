package com.example.smartnutrition.domain.repository


import com.example.smartnutrition.data.remote.dto.NutritionResponse
import kotlinx.coroutines.flow.Flow

interface NutritionRepository {
    suspend fun getDailyNutrition(userId: String): Flow<Result<NutritionResponse>>
    suspend fun getMonthlyNutrition(userId: String): Flow<Result<NutritionResponse>>
    suspend fun getDailyHistory(userId: String): Flow<Result<NutritionResponse>>
    suspend fun getMonthlyHistory(userId: String): Flow<Result<NutritionResponse>>
    suspend fun getFruitNutrition(userId: String): Flow<Result<NutritionResponse>>
}