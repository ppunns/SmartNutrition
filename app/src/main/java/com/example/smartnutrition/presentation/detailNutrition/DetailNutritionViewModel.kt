package com.example.smartnutrition.presentation.detailNutrition

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartnutrition.data.manager.TokenManager
import com.example.smartnutrition.domain.usecases.Nutrition.AddNutritionUseCase
import com.example.smartnutrition.domain.usecases.Nutrition.GetFruitNutritionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailNutritionViewModel @Inject constructor(
    private val getFruitNutritionUseCase: GetFruitNutritionUseCase,
    private val addNutritionUsecase : AddNutritionUseCase,
    private val tokenManager: TokenManager
) : ViewModel() {
    private val _state = MutableStateFlow(DetailNutritionState())
    val state: StateFlow<DetailNutritionState> = _state

    fun getNutritionByLabel(label: String) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            try {
                getFruitNutritionUseCase(label).collectLatest { result ->
                    result.fold(
                        onSuccess = { response ->
                            _state.value = _state.value.copy(
                                isLoading = false,
                                nutritionData = response,
                                error = null
                            )
                        },
                        onFailure = { exception ->
                            _state.value = _state.value.copy(
                                isLoading = false,
                                nutritionData = null,
                                error = exception.message ?: "Terjadi kesalahan saat mengambil data nutrisi"
                            )
                        }
                    )
                }
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    isLoading = false,
                    nutritionData = null,
                    error = e.message ?: "Terjadi kesalahan saat mengambil data nutrisi"
                )
            }
        }
    }

    fun addNutrition(userId: String, fruitLabel: String, quantity: Int) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isAddingData = true)
            try {
                addNutritionUsecase(userId, fruitLabel, quantity)
                    .collect { result ->
                        result.fold(
                            onSuccess = { response ->
                                _state.value = _state.value.copy(
                                    isAddingData = false,
                                    addSuccess = true
                                )
                                // Refresh data setelah berhasil menambah
                                getNutritionByLabel(fruitLabel)
                            },
                            onFailure = { exception ->
                                _state.value = _state.value.copy(
                                    isAddingData = false,
                                    error = exception.message
                                )
                            }
                        )
                    }
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    isAddingData = false,
                    error = e.message
                )
            }
        }
    }
    init {
        loadUserData()
    }

    private fun loadUserData() {
        viewModelScope.launch {
            try {
                tokenManager.getUserData.collect{ user ->
                    _state.update { currentState ->
                        currentState.copy(
                            id = user.id,
                            username = user.name,
                            email = user.email,
                            profilePicture = user.profile_picture,
                            isLoading = false
                        )
                    }
                }
            } catch (e: Exception) {
                _state.update { it.copy(error = e.message, isLoading = false) }
            }
        }
    }
}

