package com.example.remote


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.remote.composable.BottomNavigationBar
import com.example.remote.navigation.NavHostContainer
import com.example.remote.screens.onboarding.OnboardingScreen
import com.example.remote.ui.theme.RemoteTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RemoteTheme {
                NavHostContainer()
            }
        }
    }
}

@Composable
fun App() {
//    val navController = rememberNavController()
//    val goNext = remember { mutableStateOf(false) }
//    Scaffold(
//        bottomBar = {
//            if (goNext.value) BottomNavigationBar(navController)
//        },
//        modifier = Modifier.fillMaxSize()
//    ) { innerPadding ->
//        OnboardingScreen {
//            goNext.value = true
//        }
//        if (goNext.value)
//            NavHostContainer(navController,innerPadding)
//    }
}

