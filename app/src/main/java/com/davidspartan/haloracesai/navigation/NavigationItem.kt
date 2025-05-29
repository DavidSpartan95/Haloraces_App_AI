package com.davidspartan.haloracesai.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
data class NavigationItem(
    val title: String,
    val icon: ImageVector,
    val route: String
)

val navigationItems = listOf(
    NavigationItem(
        title = "Ask AI",
        icon = Icons.Default.Info,
        route = Screen.Home.rout
    ),
    NavigationItem(
        title = "Runners",
        icon = Icons.Default.Person,
        route = Screen.Profile.rout
    ),
)

sealed class Screen(val rout: String) {
    object Home: Screen("home_screen")
    object Profile: Screen("profile_screen")
}

