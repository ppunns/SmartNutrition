package com.example.smartnutrition.presentation.register


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartnutrition.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {
    private val _state = MutableStateFlow(RegisterState())
    val state = _state.asStateFlow()

    fun onUsernameChange(username: String) {
        _state.update { it.copy(username = username) }
    }

    fun onEmailChange(email: String) {
        _state.update { it.copy(email = email) }
    }

    fun onPasswordChange(password: String) {
        _state.update { it.copy(password = password) }
    }

    fun onConfirmPasswordChange(confirmPassword: String) {
        _state.update { it.copy(confirmPassword = confirmPassword) }
    }

    fun register() {
        val currentState = _state.value

        if (currentState.username.isBlank()) {
            _state.update { it.copy(error = "Username tidak boleh kosong") }
            return
        }

        if (currentState.password != currentState.confirmPassword) {
            _state.update { it.copy(error = "Password tidak cocok") }
            return
        }

        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            repository.register(
                username = currentState.username,
                email = currentState.email,
                password = currentState.password
            ).onSuccess { response ->
                _state.update {
                    it.copy(
                        isSuccess = true,
                        isLoading = false
                    )
                }
            }.onFailure { exception ->
                _state.update {
                    it.copy(
                        error = exception.message ?: "Terjadi kesalahan",
                        isLoading = false
                    )
                }
            }
        }
    }
}