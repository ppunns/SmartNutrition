package com.example.smartnutrition.presentation.detailNutrition

import com.example.smartnutrition.data.remote.dto.NutritionResponse

data class DetailNutritionState(
    val isLoading: Boolean = false,
    val nutritionData: NutritionResponse? = null,
    val error: String? = null
)