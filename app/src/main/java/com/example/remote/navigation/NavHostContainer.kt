package com.example.remote.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.remote.screens.discover.DiscoverScreen
import com.example.remote.screens.settings.PremiumScreen
import com.example.remote.screens.remote.RemoteScreen

@Composable
fun NavHostContainer(navController: NavHostController, innerPadding: PaddingValues) {
    NavHost(
        modifier = Modifier.padding(),
        navController = navController,
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
            content = { PremiumScreen() }
        )

    }
}