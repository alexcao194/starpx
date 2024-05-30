package com.alexcao.starpx.screen.image_detail

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.core.content.ContextCompat.startActivity
import androidx.core.content.FileProvider
import androidx.navigation.NavController
import coil.Coil
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.request.SuccessResult
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

@Composable
fun ImageDetailScreen(
    url: String,
    navController: NavController,
    context: Context
) {

    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        floatingActionButton = {
            Button(onClick = {
                coroutineScope.launch {
                    shareImageFromUrl(context, url)
                }
            }) {
                Icon(
                    Icons.Default.Share,
                    contentDescription = null
                )
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier.padding(padding),
            contentAlignment = Alignment.Center
        ) {
            var isLoading by remember { mutableStateOf(true) }

            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = url,
                contentDescription = null,
                imageLoader = ImageLoader(context),
                onSuccess = {
                    isLoading = false
                },
            )

            if (isLoading) {
                CircularProgressIndicator()
            }
        }
    }
}

private suspend fun shareImageFromUrl(context: Context, imageUrl: String) {
    val imageLoader = ImageLoader(context)

    val request = ImageRequest.Builder(context)
        .data(imageUrl)
        .allowHardware(false) // Disable hardware bitmaps for drawing to a Canvas
        .build()

    val result = (imageLoader.execute(request) as SuccessResult).drawable
    val bitmap = (result as BitmapDrawable).bitmap
    val uri = saveBitmapToCache(context, bitmap)

    val shareIntent = Intent().apply {
        action = Intent.ACTION_SEND
        extras?.putString(Intent.EXTRA_STREAM, uri.toString())
        type = "image/jpeg"
        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
    }

    context.startActivity(Intent.createChooser(shareIntent, "Share image via"))
}

private fun saveBitmapToCache(context: Context, bitmap: Bitmap): Uri {
    val cachePath = File(context.externalCacheDir, "my_images/")
    cachePath.mkdirs()
    val file = File(cachePath, "shared_image.jpg")
    val fileOutputStream: FileOutputStream
    try {
        fileOutputStream = FileOutputStream(file)
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream)
        fileOutputStream.close()
    } catch (e: IOException) {
        e.printStackTrace()
    }
    return FileProvider.getUriForFile(context, "${context.packageName}.provider", file)
}