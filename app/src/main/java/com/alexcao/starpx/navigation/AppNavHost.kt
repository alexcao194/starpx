package com.alexcao.starpx.config.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.alexcao.starpx.app.presentation.screen.home.HomeScreen
import com.alexcao.starpx.app.presentation.screen.login.LoginScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    NavHost(navController = navController, NavigationItem.Home.route, modifier = modifier) {
        composable(NavigationItem.Home.route) {
            HomeScreen(
                navController = navController
            )
        }
        composable(NavigationItem.Login.route) {
            LoginScreen(
                navController = navController
            )
        }
    }
}