package com.alexcao.starpx.screen.home

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val homeUiState = viewModel.homeUiState.collectAsState()
    val data = homeUiState.value.data
    Scaffold {padding ->
        LazyVerticalGrid(
            modifier = Modifier.padding(padding),
            columns = GridCells.Adaptive(minSize = 128.dp)
        ) {
            items(data.size) {index ->
                ImageCell(thumbnail = data[index].imageDetail.thumbs.medium)
            }
        }
    }
}

@Composable
fun ImageCell(
    modifier: Modifier = Modifier,
    thumbnail: String
) {
    Text(text = thumbnail, modifier = modifier.padding(16.dp))
}