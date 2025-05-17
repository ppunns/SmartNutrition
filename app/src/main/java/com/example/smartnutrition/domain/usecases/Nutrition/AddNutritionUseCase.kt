package com.example.smartnutrition.domain.usecases.Nutrition

import com.example.smartnutrition.data.remote.dto.NutritionResponse
import com.example.smartnutrition.domain.repository.NutritionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AddNutritionUseCase @Inject constructor(
    private val repository: NutritionRepository
) {
    suspend operator fun invoke(userId: String, fruitLabel: String, quantity: Int):Flow<Result<NutritionResponse>>{
        return repository.addNutrition(userId,fruitLabel,quantity)
    }
}