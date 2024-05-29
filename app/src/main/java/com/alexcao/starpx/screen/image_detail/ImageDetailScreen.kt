package com.alexcao.starpx.screen.image_detail

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import coil.ImageLoader
import coil.compose.AsyncImage

@Composable
fun ImageDetailScreen(
    url: String,
    navController: NavController,
    context: Context
) {
    Scaffold { padding ->
        Box(modifier = Modifier.padding(padding)) {
            AsyncImage(
                model = url,
                contentDescription = null,
                imageLoader = ImageLoader(context)
            )
        }
    }

}