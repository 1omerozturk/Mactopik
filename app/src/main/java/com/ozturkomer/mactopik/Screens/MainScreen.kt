package com.ozturkomer.mactopik.Screens


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.ozturkomer.mactopik.BottomNavigation.BottomNavItem
import com.ozturkomer.mactopik.BottomNavigation.BottomNavigationBar
import com.ozturkomer.mactopik.BottomNavigation.Navigation
import kotlinx.coroutines.launch

@Composable
fun MainScreen() {
    val navController =
        rememberNavController() // Burada tanımlıyorsun ama Navigation'a vermiyorsun!

    // Burada ekle!

    val pagerState = rememberPagerState(0) { 5 }
    val coroutineScope = rememberCoroutineScope()
    val pages = listOf(
        BottomNavItem.Matches.route,
        BottomNavItem.LeaderBoard.route,
        BottomNavItem.TopScorers.route,
        BottomNavItem.Home.route,
        BottomNavItem.Teams.route
    )

    var currentRoute = remember { mutableStateOf(BottomNavItem.Home.route) }

    LaunchedEffect(pagerState.currentPage) {
        currentRoute.value = pages[pagerState.currentPage]
    }
    Navigation(navController)
    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                currentRoute = currentRoute.value,
                onItemSelected = { route ->
                    currentRoute.value = route

                    val pageIndex = pages.indexOf(route)
                    if (pageIndex != -1) {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(pageIndex)
                        }
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier.padding(innerPadding)
        ) {
            HorizontalPager(
                state = pagerState,
                pageSize = PageSize.Fill,
                modifier = Modifier.fillMaxSize()
            ) { page ->
                when (page) {
                    0 -> MatchScreen()
                    1 -> LeaderBoardScreen()
                    2 -> TopScoresScreen()
                    3 -> FixtureScreen()
                    4 -> TeamsScreen(navController = navController) // Burada navController gönderildiğinden emin ol
                }
            }
        }
    }
}

