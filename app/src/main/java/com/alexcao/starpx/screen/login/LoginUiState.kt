package com.alexcao.starpx.screen.login

data class LoginUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val isSuccessful: Boolean = false,
)