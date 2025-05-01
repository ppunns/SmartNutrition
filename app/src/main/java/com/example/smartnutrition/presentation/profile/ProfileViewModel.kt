package com.example.smartnutrition.presentation.profile

import androidx.lifecycle.ViewModel
import com.example.smartnutrition.data.manager.TokenManager
import com.example.smartnutrition.data.remote.dto.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val tokenManager: TokenManager
) : ViewModel() {
    private val _state = MutableStateFlow(ProfileState())
    val state = _state.asStateFlow()


    fun toggleDarkMode() {
        _state.update { it.copy(isDarkMode = !it.isDarkMode) }
    }

    fun updateLanguage(language: String) {
        _state.update { it.copy(selectedLanguage = language) }
    }

    fun updateProfile(username: String, email: String) {
        _state.update {
            it.copy(
                username = username,
                email = email
            )
        }
    }

    fun logout() {
        tokenManager.deleteToken()
    }
}