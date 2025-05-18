package com.example.smartnutrition.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartnutrition.data.manager.TokenManager
import com.example.smartnutrition.domain.manger.LocalUserManger
import com.example.smartnutrition.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val tokenManager: TokenManager,
) : ViewModel() {
    private val _state = MutableStateFlow(ProfileState())
    val state = _state.asStateFlow()
    fun toggleDarkMode() {
        _state.update { it.copy(isDarkMode = !it.isDarkMode) }
    }
    private val _proteinTarget = MutableStateFlow(0)
    val proteinTarget = _proteinTarget.asStateFlow()
    init {
        loadUserData()
        viewModelScope.launch {
            tokenManager.getProteinTarget.collect { target ->
                _proteinTarget.value = target
            }
        }
    }

    private fun loadUserData() {
        viewModelScope.launch {
            try {
                tokenManager.getUserData.collect{ user ->
                    _state.update { currentState ->
                        currentState.copy(
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
    fun updateProteinTarget(target: Int) {
        viewModelScope.launch {
            tokenManager.saveProteinTarget(target)
        }
    }

    fun logout() {
        viewModelScope.launch {
            tokenManager.clearToken()
        }
    }
}