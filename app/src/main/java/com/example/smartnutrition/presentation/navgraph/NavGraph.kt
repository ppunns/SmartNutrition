package com.example.smartnutrition.presentation.navgraph

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.smartnutrition.presentation.onboarding.OnBoardingScreen
import com.example.smartnutrition.presentation.onboarding.OnBoardingViewModel

@Composable
fun NavGraph(
    starDestination: String
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = starDestination) {
        navigation(
            route = Route.AppStartNavigation.route,
            startDestination = Route.OnBoardingScreen.route
        ) {
            composable(route = Route.OnBoardingScreen.route) {
                val viewModel: OnBoardingViewModel = hiltViewModel()
                OnBoardingScreen(onEvent = viewModel::onEvent)
            }
        }

        navigation(
            route = Route.NewsNavigation.route,
            startDestination = Route.HomeScreen.route
        ) {
            composable(route = Route.HomeScreen.route) {
            }
            composable(route = Route.DetailsScreen.route) {
            }
        }
    }
}