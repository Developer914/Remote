package com.example.remote.navigation

import com.example.remote.R

sealed class BottomNavItem(val route: String, val icon: Int, val label: String) {
    object Discover : BottomNavItem(NavDestinations.DISCOVER_SCREEN, R.drawable.discover, "Discover")
    object Remote : BottomNavItem(NavDestinations.REMOTE_SCREEN, R.drawable.remote, "Remote")
    object Cast : BottomNavItem(NavDestinations.CAST_SCREEN, R.drawable.cast, "Cast")
    object Settings : BottomNavItem(NavDestinations.SETTINGS_SCREEN, R.drawable.settings, "Settings")
}

val bottomNavItems = listOf(
    BottomNavItem.Discover,
    BottomNavItem.Remote,
    BottomNavItem.Cast,
    BottomNavItem.Settings
)