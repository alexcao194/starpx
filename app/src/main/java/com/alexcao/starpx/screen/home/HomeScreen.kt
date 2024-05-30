package com.alexcao.starpx.screen.home

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.alexcao.starpx.navigation.NavigationItem
import java.net.URLEncoder

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel(),
    context: Context
) {
    val imageSets = viewModel.pagingDataFlow.collectAsLazyPagingItems()

    Scaffold(
        containerColor = Color.Black,
        floatingActionButton = {
            Button(
                onClick = {
                    viewModel.logout()
                    navController.navigate(NavigationItem.Login.route) {
                        popUpTo(NavigationItem.Home.route) {
                            inclusive = true
                        }
                    }
                }
            ) {
                Text(text = "Logout")
            }
        }
    ) { padding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyVerticalGrid(
                modifier = Modifier.padding(padding),
                columns = GridCells.Fixed(3),
                contentPadding = PaddingValues(2.dp),
            ) {
                items(
                    imageSets.itemCount,
                    key = { index -> imageSets[index]!!.setId }
                ) { index ->
                    val url = imageSets[index]!!.imageDetail.thumbs.small
                    AsyncImage(
                        modifier = Modifier.clickable(
                            onClick = {
                                val fullUrl = imageSets[index]!!.imageDetail.fullUrl
                                val encodedUrl = URLEncoder.encode(fullUrl, "utf-8")
                                navController.navigate(
                                    "${NavigationItem.ImageDetail.route}/$encodedUrl"
                                )
                            }
                        ),
                        model = url,
                        contentDescription = null,
                        contentScale = ContentScale.Crop
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

                        navController.navigate(NavigationItem.Login.route) {
                            popUpTo(NavigationItem.Home.route) {
                                inclusive = true
                            }
                        }

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