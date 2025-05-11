package com.example.smartnutrition.domain.usecases.Nutrition

import com.example.smartnutrition.data.remote.dto.DailyNutritionResponse
import com.example.smartnutrition.domain.repository.NutritionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetDailyNutritionUseCase @Inject constructor(
    private val repository: NutritionRepository
) {
    suspend operator fun invoke(userId: String): Flow<Result<DailyNutritionResponse>> {
        return repository.getDailyNutrition(userId)
    }
}