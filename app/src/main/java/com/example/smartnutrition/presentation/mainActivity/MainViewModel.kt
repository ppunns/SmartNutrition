package com.example.smartnutrition.presentation.mainActivity

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartnutrition.domain.usecases.app_entry.AppEntryUseCases
import com.example.smartnutrition.presentation.navgraph.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val appEntryUseCases: AppEntryUseCases
):ViewModel() {
    private val _splashCondition = mutableStateOf(true)
    val splashCondition:State<Boolean> = _splashCondition

    private val _startDestination = mutableStateOf(Route.AppStartNavigation.route)
    val startDestination: State<String> = _startDestination

    init {
        appEntryUseCases.readAppEntry().onEach { shouldStartFromHomeScreen ->
            if(shouldStartFromHomeScreen){
                _startDestination.value = Route.HomeScreen.route  // Changed from NewsNavigation to HomeScreen
            }else{
                _startDestination.value = Route.AppStartNavigation.route
            }
            delay(200)
            _splashCondition.value = false
        }.launchIn(viewModelScope)
    }
}