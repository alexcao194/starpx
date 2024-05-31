package com.alexcao.starpx.screen.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.alexcao.starpx.R
import com.alexcao.starpx.navigation.NavigationItem

@Composable
fun SplashScreen(
    navController: NavController,
    viewModel: SplashViewModel = hiltViewModel()
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(painter = painterResource(id = R.drawable.ic_launcher_foreground), contentDescription = null)
    }

    if (viewModel.isLoggedIn()) {
        navController.navigate(NavigationItem.Home.route) {
            popUpTo(NavigationItem.Splash.route) {
                inclusive = true
            }
        }
    } else {
        navController.navigate(NavigationItem.Login.route) {
            popUpTo(NavigationItem.Splash.route) {
                inclusive = true
            }
        }
    }
}