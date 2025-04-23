package com.example.smartnutrition.presentation.login


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartnutrition.domain.model.User
import com.example.smartnutrition.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {
    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            
            repository.login(email, password)
                .onSuccess { response ->
                    _state.update { 
                        it.copy(
                            isSuccess = true,
                            isLoading = false
                        )
                    }
                }
                .onFailure { exception ->
                    _state.update {
                        it.copy(
                            error = exception.message ?: "Unknown error occurred",
                            isLoading = false
                        )
                    }
                }
        }
    }
}
