package com.example.smartnutrition.domain.usecases.Nutrition

import com.example.smartnutrition.data.remote.dto.NutritionResponse
import com.example.smartnutrition.domain.repository.NutritionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject



class GetFruitNutritionUseCase @Inject constructor(
    private val repository: NutritionRepository
) {
    suspend operator fun invoke(label: String): NutritionResponse {
        return repository.getNutritionByLabel(label)
    }
}