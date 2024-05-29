package com.alexcao.starpx.screen.home

import com.alexcao.starpx.model.ImageSet

data class HomeUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val data: List<ImageSet> = emptyList()
)