package com.example.smartnutrition.presentation.navgraph

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class Route(
    val route: String,
    val arguments: List<NamedNavArgument> = emptyList()
) {
    object SplashScreen : Route("splashScreen")
    object OnBoardingScreen: Route(route = "onBoardingScreen")
    object LoginScreen : Route("loginScreen")
    object RegisterScreen : Route("register_screen")
    object HomeScreen: Route(route = "homeScreen")
    object ProfileScreen: Route(route = "profileScreen")
    object CameraScanning : Route("camera_scanning")
    object DetailsScreen : Route("details_screen/{label}") {
        fun createRoute(label: String) = "details_screen/$label"
    }
    object AppStartNavigation : Route(route = "appStartNavigation")
    object NewsNavigation : Route(route = "newsNavigation")
}
