package com.example.remote.navigation

import com.example.remote.R

sealed class NavItem(val route: String, val icon: Int, val label: String) {
    object Discover : NavItem(NavDestinations.DISCOVER_SCREEN, R.drawable.discover, "Discover")
    object Remote : NavItem(NavDestinations.REMOTE_SCREEN, R.drawable.remote, "Remote")
    object Cast : NavItem(NavDestinations.CAST_SCREEN, R.drawable.cast, "Cast")
    object Settings : NavItem(NavDestinations.SETTINGS_SCREEN, R.drawable.settings, "Settings")
}

val navItems = listOf(
    NavItem.Discover,
    NavItem.Remote,
    NavItem.Cast,
    NavItem.Settings
)