package com.example.smartnutrition.presentation.home

import com.example.smartnutrition.data.remote.dto.DailyNutritionResponse

data class HomeState(
    val isLoading: Boolean = false,
    val data: DailyNutritionResponse? = null,
    val error: String? = null
)
