package com.alexcao.starpx

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.alexcao.starpx.navigation.AppNavHost
import com.alexcao.starpx.service.initializeAWSMobileClient
import com.alexcao.starpx.ui.theme.StarpxTheme
import com.amazonaws.mobile.client.AWSMobileClient
import com.amazonaws.mobile.config.AWSConfiguration

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeAWSMobileClient(applicationContext)
        enableEdgeToEdge()
        setContent {
            StarpxTheme {
                AppNavHost()
            }
        }
    }
}