package com.alexcao.starpx.screen.home

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
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
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.ImageLoader
import coil.compose.AsyncImage
import com.alexcao.starpx.navigation.NavigationItem

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
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyVerticalGrid(
                modifier = Modifier.padding(padding),
                columns = GridCells.Adaptive(minSize = 128.dp),
            ) {
                items(
                    imageSets.itemCount,
                ) { index ->
                    ImageCell(
                        modifier = Modifier.padding(2.dp),
                        thumbnail = imageSets[index]!!.imageDetail.thumbs.small,
                        context = context,
                        onClick = {
                            navController.navigate(
                                "${NavigationItem.ImageDetail.route}?url=${imageSets[index]!!.imageDetail.fullUrl}"
                            )
                        }
                    )
                }

                when {
                    imageSets.loadState.refresh is LoadState.Loading -> {
                        item {
                            Text(text = "Loading...", color = Color.White)
                        }
                    }

                    imageSets.loadState.append is LoadState.Loading -> {
                        item {
                            Text(text = "Loading...", color = Color.White)
                        }
                    }

                    imageSets.loadState.refresh is LoadState.Error -> {
                        val error = imageSets.loadState.refresh as LoadState.Error
                        item {
                            Text(
                                text = "Error: ${error.error.localizedMessage}",
                                color = Color.Red
                            )
                        }
                    }

                    imageSets.loadState.append is LoadState.Error -> {
                        val error = imageSets.loadState.append as LoadState.Error
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
    context: Context,
    onClick: () -> Unit
) {
    Button(onClick = {
        onClick()
    }) {
        AsyncImage(
            modifier = modifier,
            model = thumbnail,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            imageLoader = ImageLoader(context),
        )
    }
}