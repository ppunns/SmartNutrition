package com.example.smartnutrition.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartnutrition.data.manager.TokenManager
import com.example.smartnutrition.domain.usecases.Nutrition.NutritionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val tokenManager: TokenManager,
    private val nutritionUseCase: NutritionUseCase
) : ViewModel() {
    private val _historyState = mutableStateOf(HomeState())
    val historyState: State<HomeState> = _historyState

    init {
        fetchDailyHistory()
    }

    private fun fetchDailyHistory() {
        viewModelScope.launch {
            try {
//                val userId = tokenManager.getUserId() ?: return@launch
                nutritionUseCase.getDailyNutritionUseCase("user1245").collect { result ->
                    result.onSuccess { response ->
                        _historyState.value = historyState.value.copy(
                            isLoading = false,
                            data = response,
                            error = null
                        )
                    }.onFailure { exception ->
                        _historyState.value = historyState.value.copy(
                            isLoading = false,
                            error = exception.message ?: "Terjadi kesalahan"
                        )
                    }
                }
            } catch (e: Exception) {
                _historyState.value = historyState.value.copy(
                    isLoading = false,
                    error = e.message ?: "Terjadi kesalahan"
                )
            }
        }
    }
//    private val _nutritionState = MutableStateFlow(HomeState())
//    val nutritionState = _nutritionState.asStateFlow()
//
//    init {
//        getDailyNutrition()
//    }
//
//    private fun getDailyNutrition() {
//        viewModelScope.launch {
//            _nutritionState.update { it.copy(isLoading = true) }
//            val userId = tokenManager.getUserId() ?: return@launch
//            nutritionUseCase.getDailyNutritionUseCase(userId).collect { result ->
//                result.onSuccess { response ->
//                    _nutritionState.update {
//                        it.copy(
//                            isLoading = false,
//                            data = response,
//                            error = null
//                        )
//                    }
//                }
//                    .onFailure { exception ->
//                        _nutritionState.update {
//                            it.copy(
//                                isLoading = false,
//                                error = exception.message
//                            )
//                        }
//                    }
//            }
//
//        }
//    }
}
