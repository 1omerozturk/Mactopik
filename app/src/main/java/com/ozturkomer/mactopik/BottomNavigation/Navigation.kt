package com.ozturkomer.mactopik.BottomNavigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ozturkomer.mactopik.Screens.HomeScreen
import com.ozturkomer.mactopik.Screens.LeaderBoardScreen
import com.ozturkomer.mactopik.Screens.MatchScreen
import com.ozturkomer.mactopik.Screens.TeamDetailScreen
import com.ozturkomer.mactopik.Screens.TeamsScreen

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController, startDestination = BottomNavItem.Home.route) {
        composable(BottomNavItem.Home.route) { HomeScreen() }
        composable(BottomNavItem.Profile.route) { LeaderBoardScreen() }
        composable(BottomNavItem.Teams.route) { TeamsScreen(navController) }
        composable(BottomNavItem.Settings.route) { MatchScreen() }

        composable(
            route = "card_detail/{id}",
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id") ?: ""
            TeamDetailScreen(teamId = id)
        }

    }
}
