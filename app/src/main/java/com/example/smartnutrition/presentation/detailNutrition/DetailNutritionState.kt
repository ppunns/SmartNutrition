package com.example.smartnutrition.presentation.detailNutrition

import com.example.smartnutrition.data.remote.dto.NutritionResponse

data class DetailNutritionState(
    val isLoading: Boolean = false,
    val nutritionData: NutritionResponse? = null,
    val error: String? = null,
    val isAddingData: Boolean = false,
    val addSuccess: Boolean = false,
    val id: Int = 0,
    val proteinTarget: Int = 0,
    val username: String = "",
    val email: String = "",
    val profilePicture: String = "",
)