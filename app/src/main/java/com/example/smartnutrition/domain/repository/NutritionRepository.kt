package com.example.smartnutrition.domain.repository


import com.example.smartnutrition.data.model.FruitRequest
import com.example.smartnutrition.data.remote.dto.NutritionResponse
import com.example.smartnutrition.domain.model.Data
import kotlinx.coroutines.flow.Flow

interface NutritionRepository {
    suspend fun getDailyNutrition(userId: String): Flow<Result<NutritionResponse>>
    suspend fun getMonthlyNutrition(userId: String): Flow<Result<NutritionResponse>>
    suspend fun getDailyHistory(userId: String): Flow<Result<NutritionResponse>>
    suspend fun getMonthlyHistory(userId: String): Flow<Result<NutritionResponse>>
//    suspend fun getFruitNutrition(label: String): Result<NutritionResponse>
    suspend fun getNutritionByLabel(label: String): NutritionResponse
}