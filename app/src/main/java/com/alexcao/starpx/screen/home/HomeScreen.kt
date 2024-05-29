package com.alexcao.starpx.screen.home

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.ImageLoader
import coil.compose.AsyncImage

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel(),
    context: Context
) {
    val homeUiState = viewModel.homeUiState.collectAsState()
    val data = homeUiState.value.data
    val isLoading = homeUiState.value.isLoading
    val error = homeUiState.value.error

    LaunchedEffect(error) {
        error?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            viewModel.clearError()
        }
    }

    Scaffold(
        containerColor = Color.Black
    ) { padding ->
        Column {
            LazyVerticalGrid(
                modifier = Modifier.padding(padding),
                columns = GridCells.Adaptive(minSize = 128.dp),
            ) {
                items(
                    data.size,
                    key = { index -> data[index].setId }
                ) { index ->
                    ImageCell(
                        modifier = Modifier.padding(2.dp),
                        thumbnail = data[index].imageDetail.thumbs.small,
                        context = context
                    )
                }
            }

            if (isLoading) {
                CircularProgressIndicator()
            }

            if (error != null) {
                Text(
                    text = error,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun ImageCell(
    modifier: Modifier = Modifier,
    thumbnail: String,
    context: Context
) {
    AsyncImage(
        modifier = modifier,
        model = thumbnail,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        imageLoader = ImageLoader(context),
    )
}