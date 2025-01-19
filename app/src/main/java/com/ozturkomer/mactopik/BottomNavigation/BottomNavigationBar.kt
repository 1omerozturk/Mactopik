package com.ozturkomer.mactopik.BottomNavigation


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons

import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

import androidx.compose.ui.unit.dp

import com.ozturkomer.mactopik.components.Leaderboard
import com.ozturkomer.mactopik.components.Sports_and_outdoors


sealed class BottomNavItem(val label: String, val route: String, val icon: ImageVector) {
    object Home : BottomNavItem("Fikstür", "fixture", Sports_and_outdoors)
    object Profile : BottomNavItem("Sıralama", "leaderbord", Leaderboard)
    object Settings : BottomNavItem("Maçlar", "matches", Icons.Default.Settings)
    object Teams : BottomNavItem("Takımlar", "teams", Icons.Default.Person)

}


@Composable
fun BottomNavigationBar(
    currentRoute: String, onItemSelected: (String) -> Unit
) {
    val items = listOf(
        BottomNavItem.Home, BottomNavItem.Profile, BottomNavItem.Settings, BottomNavItem.Teams
    )
    BottomNavigation(
        backgroundColor = Color.White, contentColor = Color.Black
    ) {
        items.forEach { item ->
            val isSelected = currentRoute == item.route
            BottomNavigationItem(
                icon = { Icon(item.icon, contentDescription = item.label) },
                label = { Text(item.label) },
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

