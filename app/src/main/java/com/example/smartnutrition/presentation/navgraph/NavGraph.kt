package com.example.smartnutrition.presentation.navgraph

import androidx.camera.core.ImageCapture
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.smartnutrition.presentation.home.HomeScreen
import com.example.smartnutrition.presentation.home.HomeViewModel
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

        composable(route = Route.HomeScreen.route) {
            val viewModel: HomeViewModel = hiltViewModel()
            HomeScreen (
                articles = viewModel.news.collectAsLazyPagingItems(),
                navigate = { url ->
                    navController.navigate(Route.DetailsScreen.route)
                }
            )
        }
        composable(route = Route.CameraScanning.route) {
            
        }

        composable(route = Route.DetailsScreen.route) {
            // Details screen implementation
        }
    }
}