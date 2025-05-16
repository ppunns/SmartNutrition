package com.example.smartnutrition.presentation.detailNutrition

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartnutrition.domain.usecases.Nutrition.GetFruitNutritionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailNutritionViewModel @Inject constructor(
    private val getFruitNutritionUseCase: GetFruitNutritionUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(DetailNutritionState())
    val state: StateFlow<DetailNutritionState> = _state

    fun getNutritionByLabel(label: String) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            try {
                val result = getFruitNutritionUseCase("pir")
                _state.value = _state.value.copy(
                    isLoading = false,
                    nutritionData = result,
                    error = null
                )
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = e.message ?: "Terjadi kesalahan saat mengambil data nutrisi"
                )
            }
        }
    }
}