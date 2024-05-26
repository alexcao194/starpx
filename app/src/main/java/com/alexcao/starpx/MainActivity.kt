package com.alexcao.starpx

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.alexcao.starpx.config.navigation.AppNavHost
import com.alexcao.starpx.ui.theme.StarpxTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StarpxTheme {
                AppNavHost()
            }
        }
    }
}