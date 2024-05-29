package com.alexcao.starpx.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.alexcao.starpx.screen.home.HomeScreen
import com.alexcao.starpx.screen.login.LoginScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    context: Context
) {
    val navController = rememberNavController()
    NavHost(navController = navController, NavigationItem.Login.route, modifier = modifier) {
        composable(NavigationItem.Home.route) {
            HomeScreen(
                navController = navController,
                context = context
            )
        }
        composable(NavigationItem.Login.route) {
            LoginScreen(
                navController = navController
            )
        }
    }
}