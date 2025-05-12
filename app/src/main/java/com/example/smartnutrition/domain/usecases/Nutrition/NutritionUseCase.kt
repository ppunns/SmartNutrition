package com.example.smartnutrition.domain.usecases.Nutrition

import com.example.smartnutrition.domain.usecases.app_entry.ReadAppEntry

data class NutritionUseCase(
    val getDailyNutritionUseCase: GetDailyNutritionUseCase,
    val getMonthlyNutritionUseCase: GetMonthlyNutritionUseCase,
    val getDailyHistoryNutritionUseCase: GetDailyHistoryNutritionUseCase,
    val getMonthlyHistoryNutritionUseCase: GetMonthlyHistoryNutritionUseCase
)