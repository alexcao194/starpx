package com.alexcao.starpx.screen.home.component

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.ImageLoader
import coil.compose.AsyncImage

@Composable
fun ImageCell(
    modifier: Modifier = Modifier,
    thumbnail: String,
    context: Context,
    onClick: () -> Unit
) {
    AsyncImage(
        modifier = modifier.clickable {
            onClick()
        },
        model = thumbnail,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        imageLoader = ImageLoader(context),
    )
}