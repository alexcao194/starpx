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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import coil.ImageLoader
import coil.compose.AsyncImage

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel(),
    context: Context
) {
    val imageSets = viewModel.pagingDataFlow.collectAsLazyPagingItems()

    Scaffold(
        containerColor = Color.Black
    ) { padding ->
        Column (
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyVerticalGrid(
                modifier = Modifier.padding(padding),
                columns = GridCells.Adaptive(minSize = 128.dp),
            ) {
                items(
                    imageSets.itemCount,
                    key = { index -> imageSets[index]!!.setId }
                ) { index ->
                    ImageCell(
                        modifier = Modifier.padding(2.dp),
                        thumbnail = imageSets[index]!!.imageDetail.thumbs.small,
                        context = context
                    )
                }

                when {
                    imageSets.loadState.refresh is androidx.paging.LoadState.Loading -> {
                        item {
                            CircularProgressIndicator()
                        }
                    }
                    imageSets.loadState.append is androidx.paging.LoadState.Loading -> {
                        item {
                            CircularProgressIndicator()
                        }
                    }
                    imageSets.loadState.refresh is androidx.paging.LoadState.Error -> {
                        val error = imageSets.loadState.refresh as androidx.paging.LoadState.Error
                        item {
                            Text(
                                text = "Error: ${error.error.localizedMessage}",
                                color = Color.Red
                            )
                        }
                    }
                    imageSets.loadState.append is androidx.paging.LoadState.Error -> {
                        val error = imageSets.loadState.append as androidx.paging.LoadState.Error
                        item {
                            Text(
                                text = "Error: ${error.error.localizedMessage}",
                                color = Color.Red
                            )
                        }
                    }
                }
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