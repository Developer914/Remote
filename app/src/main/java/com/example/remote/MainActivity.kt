package com.example.remote


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import com.example.remote.navigation.NavHostContainer
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

