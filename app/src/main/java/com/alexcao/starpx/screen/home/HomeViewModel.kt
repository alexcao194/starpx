package com.alexcao.starpx.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexcao.starpx.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    private val _homeUiState: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState())
    val homeUiState: StateFlow<HomeUiState> = _homeUiState.asStateFlow()

    init {
        loadMore()
    }

    fun loadMore() {

        viewModelScope.launch {
            _homeUiState.update { it.copy(isLoading = true) }
            val newItems = _homeUiState.value.data.toMutableList()
            newItems.addAll(repository.getImages())
            _homeUiState.update {
                it.copy(
                    isLoading = false,
                    data = newItems
                )
            }
        }
    }
}