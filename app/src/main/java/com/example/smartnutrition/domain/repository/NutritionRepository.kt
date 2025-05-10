package com.example.smartnutrition.domain.repository

import com.example.smartnutrition.data.remote.dto.DetailNutrisiResponse

interface NutritionRepository {
    suspend fun getNutritionDetail(name: String): Result<DetailNutrisiResponse>
}