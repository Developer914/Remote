package com.example.remote.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.remote.screens.discover.DiscoverScreen
import com.example.remote.screens.home.HomeScreen
import com.example.remote.screens.onboarding.OnboardingScreen
import com.example.remote.screens.remote.RemoteScreen
import com.example.remote.screens.settings.SettingScreen
import com.example.remote.screens.settings.premium.PremiumScreen

@Composable
fun NavHostContainer() {
    val navController = rememberNavController()
    NavHost(
        modifier = Modifier.padding(),
        navController = navController,
        startDestination = NavDestinations.ONBOARDING_SCREEN
    ) {
        composable(
            route = NavDestinations.ONBOARDING_SCREEN,
            content = { OnboardingScreen(navController) }
        )
        composable(
            route = NavDestinations.HOME_SCREEN,
            content = { HomeScreen(navController) }
        )
//        composable(
//            route = NavDestinations.DISCOVER_SCREEN,
//            content = { DiscoverScreen() }
//        )
//        composable(
//            route = NavDestinations.REMOTE_SCREEN,
//            content = { RemoteScreen() }
//        )
//        composable(
//            route = NavDestinations.CAST_SCREEN,
//            content = { RemoteScreen() }
//        )
//        composable(
//            route = NavDestinations.SETTINGS_SCREEN,
//            content = { SettingScreen(navController) }
//        )

        composable(
            route = NavDestinations.PREMIUM_SCREEN,
            content = { PremiumScreen() }
        )

    }
}