package com.ozturkomer.mactopik.BottomNavigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ozturkomer.mactopik.components.Leaderboard
import com.ozturkomer.mactopik.components.Scoreboard
import com.ozturkomer.mactopik.components.Shield
import com.ozturkomer.mactopik.components.Social_leaderboard
import com.ozturkomer.mactopik.components.Sports_and_outdoors


sealed class BottomNavItem(val label: String, val route: String, val icon: ImageVector) {
    object Home : BottomNavItem("Fikstür", "fixture", Sports_and_outdoors)
    object LeaderBoard : BottomNavItem("Sıralama", "leaderbord", Leaderboard)
    object TopScorers : BottomNavItem("Gol Krallığı", "topscore", Social_leaderboard)
    object Matches : BottomNavItem("Maçlar", "matches", Scoreboard)
    object Teams : BottomNavItem("Takımlar", "teams", Shield)

}


@Composable
fun BottomNavigationBar(
    currentRoute: String, onItemSelected: (String) -> Unit
) {
    val items = listOf(
        BottomNavItem.Matches,
        BottomNavItem.LeaderBoard,
        BottomNavItem.TopScorers,
        BottomNavItem.Home,
        BottomNavItem.Teams
    )
    BottomNavigation(
        backgroundColor = Color.White, contentColor = Color.Black
    ) {
        items.forEach { item ->
            val isSelected = currentRoute == item.route
            BottomNavigationItem(
                icon = { Icon(item.icon, contentDescription = item.label) },
                label = { Text(item.label, fontSize = 12.sp, textAlign = TextAlign.Center ,fontWeight = FontWeight.Bold
                ) },
                selected = isSelected,
                onClick = {
                    if (currentRoute != item.route) onItemSelected(item.route)
                },
                selectedContentColor = Color.Black,
                unselectedContentColor = Color.Gray,
                alwaysShowLabel = true,
                modifier = Modifier
                    .background(if (isSelected) Color.LightGray else Color.Transparent)
                    .padding(3.dp) // Arka plan rengi
            )
        }
    }
}

