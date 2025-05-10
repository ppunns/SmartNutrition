package com.example.smartnutrition.presentation.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.smartnutrition.presentation.navgraph.Route
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    viewModel: SplashViewModel = hiltViewModel(),
    navController: NavController
) {
    LaunchedEffect(key1 = true) {
        delay(2000L)
        if (viewModel.isTokenValid()) {
            navController.navigate(Route.HomeScreen.route) {
                popUpTo(Route.SplashScreen.route) { inclusive = true }
            }
        } else {
            navController.navigate(Route.LoginScreen.route) {
                popUpTo(Route.SplashScreen.route) { inclusive = true }
            }
        }
    }
}