package com.example.smartnutrition.presentation.navgraph

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.smartnutrition.presentation.camera.CameraScreen
import com.example.smartnutrition.presentation.home.HomeScreen
import com.example.smartnutrition.presentation.home.HomeViewModel
import com.example.smartnutrition.presentation.login.LoginScreen
import com.example.smartnutrition.presentation.onboarding.OnBoardingScreen
import com.example.smartnutrition.presentation.onboarding.OnBoardingViewModel
import com.example.smartnutrition.presentation.register.RegisterScreen

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

        composable(route = Route.HomeScreen.route
        ) {
            val viewModel: HomeViewModel = hiltViewModel()
            HomeScreen (
                articles = viewModel.news.collectAsLazyPagingItems(),
                navigate = { destination ->
                    navController.navigate(destination)
                }
            )
        }

        composable(route = Route.CameraScanning.route
        ) {
            CameraScreen(
                navigate = {destination ->
                    navController.navigate(destination)
                }
            )
        }

        composable(route = Route.DetailsScreen.route) {
            // Details screen implementation
        }
    }
}