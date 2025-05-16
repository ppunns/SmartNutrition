package com.example.smartnutrition.presentation.navgraph

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.smartnutrition.data.remote.dto.NutritionResponse
import com.example.smartnutrition.presentation.camera.CameraScreen
import com.example.smartnutrition.presentation.common.SharedViewModel
import com.example.smartnutrition.presentation.detailNutrition.DetailNutritionScreen
import com.example.smartnutrition.presentation.home.HomeScreen
import com.example.smartnutrition.presentation.home.HomeViewModel
import com.example.smartnutrition.presentation.login.LoginScreen
import com.example.smartnutrition.presentation.onboarding.OnBoardingScreen
import com.example.smartnutrition.presentation.onboarding.OnBoardingViewModel
import com.example.smartnutrition.presentation.profile.ProfileScreen
import com.example.smartnutrition.presentation.register.RegisterScreen
import com.example.smartnutrition.presentation.splash.SplashScreen

@Composable
fun NavGraph(
    startDestination: String = Route.SplashScreen.route
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination) {
        composable(route = Route.SplashScreen.route) {
            SplashScreen(navController = navController)
        }
        navigation(
            route = Route.AppStartNavigation.route,
            startDestination = Route.OnBoardingScreen.route
        ) {
            composable(route = Route.OnBoardingScreen.route,) {
                val viewModel: OnBoardingViewModel = hiltViewModel()
                OnBoardingScreen(onEvent = viewModel::onEvent)
            }
        }

        composable(
            route = Route.LoginScreen.route
        ) {
            LoginScreen(
                navController = navController
            )
        }

        composable(route = Route.RegisterScreen.route) {
            RegisterScreen(
                navController = navController
            )
        }
        composable(route = Route.ProfileScreen.route) {
            ProfileScreen(
                onNavigateBack = { navController.navigateUp() },
                onNavigateToLogin = {
                    navController.navigate(Route.LoginScreen.route) {
                        popUpTo(Route.HomeScreen.route) { inclusive = true }
                    }
                }
            )
        }
        composable(route = Route.HomeScreen.route) {
            val viewModel: HomeViewModel = hiltViewModel()
            val state = viewModel.historyState.value
            HomeScreen(
                DataNutrition = state,
                navigate = { destination -> navController.navigate(destination) }
            )
        }

        composable(route = Route.CameraScanning.route) {
            CameraScreen(
                navigate = {destination ->
                    navController.navigate(destination)
                }
            )
        }

        composable(route = Route.DetailsScreen.route) {
            val nutritionData = navController.previousBackStackEntry?.savedStateHandle?.get<NutritionResponse>("nutritionData")
            nutritionData?.let { data ->
                DetailNutritionScreen(
                    nutritionData = data,
                    onNavigateBack = { navController.navigateUp() }
                )
            }
        }
    }
}