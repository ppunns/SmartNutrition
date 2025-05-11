package com.example.smartnutrition.domain.repository

import com.example.smartnutrition.data.remote.dto.DailyNutritionResponse
import kotlinx.coroutines.flow.Flow

interface NutritionRepository {
    suspend fun getDailyNutrition(userId: String): Flow<Result<DailyNutritionResponse>>
    suspend fun getMonthlyNutrition(userId: String): Flow<Result<DailyNutritionResponse>>
    suspend fun getDailyHistory(userId: String): Flow<Result<DailyNutritionResponse>>
    suspend fun getMonthlyHistory(userId: String): Flow<Result<DailyNutritionResponse>>
    suspend fun getFruitNutrition(userId: String): Flow<Result<DailyNutritionResponse>>
}