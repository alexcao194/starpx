package com.alexcao.starpx.config.navigation

enum class Screen {
    HOME,
    LOGIN
}

sealed class NavigationItem(val route: String) {
    data object Home : NavigationItem(Screen.HOME.name)
    data object Login : NavigationItem(Screen.LOGIN.name)
}