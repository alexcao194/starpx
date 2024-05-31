package com.alexcao.starpx.screen.splash

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.alexcao.starpx.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class SplashViewModel @Inject constructor(
    private val repository: Repository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
}