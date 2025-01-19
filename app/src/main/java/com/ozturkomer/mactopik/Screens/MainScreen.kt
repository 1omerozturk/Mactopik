package com.ozturkomer.mactopik.Screens


import androidx.compose.material.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.navigation.compose.rememberNavController
import com.ozturkomer.mactopik.BottomNavigation.BottomNavItem
import com.ozturkomer.mactopik.BottomNavigation.BottomNavigationBar
import com.ozturkomer.mactopik.BottomNavigation.Navigation

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    var currentRoute by remember { mutableStateOf(BottomNavItem.Home.route) }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                currentRoute = currentRoute,
                onItemSelected = {
                    currentRoute = it
                    navController.navigate(it) {
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    ) { innerPadding ->
        Navigation(
            navController = navController
        )
    }
}
