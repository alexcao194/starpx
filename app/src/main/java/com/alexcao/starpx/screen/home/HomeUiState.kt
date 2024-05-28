package com.alexcao.starpx.screen.home

import com.alexcao.starpx.model.HomeItem

data class HomeUiState(
    val isLoading: Boolean = false,
    val error: String = "",
    val data: List<HomeItem> = emptyList()
)