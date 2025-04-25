package com.example.smartnutrition.presentation.navgraph

import androidx.navigation.NamedNavArgument

sealed class Route(
    val route: String,
    val arguments: List<NamedNavArgument> = emptyList()
) {
    object OnBoardingScreen: Route(route = "onBoardingScreen")
    object LoginScreen : Route("loginScreen")
    object RegisterScreen : Route("register_screen")
    object HomeScreen: Route(route = "homeScreen")
    object CameraScanning : Route("camera_scanning")

    object DetailsScreen : Route(route = "detailsScreen")

    object AppStartNavigation : Route(route = "appStartNavigation")
    object NewsNavigation : Route(route = "newsNavigation")
}
