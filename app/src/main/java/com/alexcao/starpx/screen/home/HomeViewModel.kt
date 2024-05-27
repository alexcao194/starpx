package com.alexcao.starpx.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexcao.starpx.model.HomeItem
import com.alexcao.starpx.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Collections.addAll
import java.util.Timer
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
        _homeUiState.update {
            it.copy(isLoading = true)
        }
        viewModelScope.launch {
            delay(2000)
            val newItems = _homeUiState.value.data.toMutableList()
            newItems.addAll(HomeItem.mockData)
            _homeUiState.update {
                it.copy(
                    isLoading = false,
                    data = newItems
                )
            }
        }
    }
}