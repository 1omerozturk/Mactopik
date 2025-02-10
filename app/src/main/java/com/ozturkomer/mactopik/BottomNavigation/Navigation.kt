package com.ozturkomer.mactopik.BottomNavigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ozturkomer.mactopik.Screens.MainScreen
import com.ozturkomer.mactopik.Screens.MatchDetailScreen
import com.ozturkomer.mactopik.Screens.TeamDetailScreen

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(
        navController,
        startDestination = "main"
    ) {
        composable("main") { MainScreen(navController) }
        composable(
            route = "match_detail/{week}/{id}",
            arguments = listOf(
                navArgument("week") { type = NavType.StringType },
                navArgument("id") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val week = backStackEntry.arguments?.getString("week") ?: "23"
            val id = backStackEntry.arguments?.getInt("id") ?: 2
            MatchDetailScreen(navController, week, id)
        }
        composable(
            route = "team_detail/{id}",
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id") ?: ""
            TeamDetailScreen(teamId = id)
        }
    }
}
