package com.alexcao.starpx.app.presentation.screen.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun HomeScreen(
    navController: NavController
) {
    Scaffold {
        Box(
            modifier = Modifier.padding(it)
        ) {
            Text(text = "Home Screen")
        }
    }
}