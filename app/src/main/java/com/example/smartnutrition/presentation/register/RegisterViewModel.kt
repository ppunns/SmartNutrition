package com.example.smartnutrition.presentation.register


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartnutrition.data.manager.TokenManager
import com.example.smartnutrition.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val repository: AuthRepository,
    private val tokenManager: TokenManager
) : ViewModel() {
    
    private val _state = MutableStateFlow(RegisterState())
    val state = _state.asStateFlow()

    private val _snackbarMessage = MutableStateFlow<String?>(null)
    val snackbarMessage = _snackbarMessage.asStateFlow()

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
            _snackbarMessage.value = "Username tidak boleh kosong"
            return
        }

        if (currentState.password != currentState.confirmPassword) {
            _state.update { it.copy(error = "Password tidak cocok") }
            _snackbarMessage.value = "Password tidak cocok"
            return
        }

        viewModelScope.launch {
            try {
                _state.update { it.copy(isLoading = true, error = null) }
                
                // Add 2 seconds delay for loading screen
                delay(2000)
                
                repository.register(
                    username = currentState.username,
                    email = currentState.email,
                    password = currentState.password
                ).onSuccess { response ->
                    // Save token after successful registration
                    tokenManager.saveToken(response.token)
                    tokenManager.saveUserData(
                        user = response.user
                    )
                    _state.update {
                        it.copy(
                            isSuccess = true,
                            isLoading = false
                        )
                    }
                    _snackbarMessage.value = "Registrasi berhasil"
                }.onFailure { exception ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = exception.message ?: "Registrasi gagal"
                        )
                    }
                    _snackbarMessage.value = "Registrasi gagal"
                }
            } catch (e: Exception) {
                _state.update {
                    it.copy(
                        isLoading = false,
                        error = e.message ?: "Terjadi kesalahan"
                    )
                }
                _snackbarMessage.value = "Registrasi gagal"
            }
        }
    }
}