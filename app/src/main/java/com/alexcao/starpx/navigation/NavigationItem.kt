package com.alexcao.starpx.navigation

enum class Screen {
    HOME,
    LOGIN
}

sealed class NavigationItem(val route: String) {
    data object Home : NavigationItem(Screen.HOME.name)
    data object Login : NavigationItem(Screen.LOGIN.name)
}