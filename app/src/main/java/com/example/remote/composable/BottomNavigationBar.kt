package com.example.remote.composable

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.remote.R
import com.example.remote.navigation.navItems
import com.example.remote.ui.theme.SelectedColor
import com.example.remote.ui.theme.TextColor

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    BottomNavigation(
        backgroundColor = Color.White
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        navItems.forEach { item ->
            BottomNavigationItem(
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route)
                    {
                        popUpTo(navController.graph.startDestinationId) // Pops to the first screen to avoid backstack buildup
                        launchSingleTop = true  // Avoids multiple instances of the same screen
                    }
                },
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = item.label
                    )
                },
                label = {
                    Text(
                        item.label, fontSize = 11.43.sp,
                        color = if (currentRoute == item.route) SelectedColor else TextColor ,
                    )
                },
                selectedContentColor = SelectedColor,
                unselectedContentColor = TextColor
            )
        }
    }
}