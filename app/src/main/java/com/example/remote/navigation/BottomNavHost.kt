package com.example.remote.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.remote.screens.discover.DiscoverScreen
import com.example.remote.screens.remote.RemoteScreen
import com.example.remote.screens.settings.SettingScreen

@Composable
fun BottomNavHost(bottomNavController: NavHostController, navController: NavHostController) {
    //val bottomNavController = rememberNavController()
    NavHost(
        modifier = Modifier.padding(),
        navController = bottomNavController,
        startDestination = NavDestinations.REMOTE_SCREEN
    ) {
        composable(
            route = NavDestinations.DISCOVER_SCREEN,
            content = { DiscoverScreen() }
        )
        composable(
            route = NavDestinations.REMOTE_SCREEN,
            content = { RemoteScreen() }
        )
        composable(
            route = NavDestinations.CAST_SCREEN,
            content = { RemoteScreen() }
        )
        composable(
            route = NavDestinations.SETTINGS_SCREEN,
            content = { SettingScreen(navController) }
        )

    }
}