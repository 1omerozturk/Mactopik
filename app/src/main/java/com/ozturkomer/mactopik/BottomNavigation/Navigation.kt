package com.ozturkomer.mactopik.BottomNavigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ozturkomer.mactopik.Screens.FixtureScreen
import com.ozturkomer.mactopik.Screens.LeaderBoardScreen
import com.ozturkomer.mactopik.Screens.MatchScreen
import com.ozturkomer.mactopik.Screens.TeamDetailScreen
import com.ozturkomer.mactopik.Screens.TeamsScreen
import com.ozturkomer.mactopik.Screens.TopScoresScreen
import com.ozturkomer.mactopik.components.SplashScreen

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(
        navController,
        startDestination = BottomNavItem.Home.route
    ) {
        composable("splash") { SplashScreen(navController = navController) }
        composable("topScorers") { TopScoresScreen() }
        composable(BottomNavItem.Home.route) { MatchScreen() }
        composable(BottomNavItem.LeaderBoard.route) { LeaderBoardScreen() }
        composable(BottomNavItem.Teams.route) { TeamsScreen(navController = navController) }
        composable(BottomNavItem.Matches.route) { FixtureScreen() }

        composable(
            route = "team_detail/{id}",
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id") ?: ""
            TeamDetailScreen(teamId = id)
        }
    }
}
