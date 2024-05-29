package com.alexcao.starpx.screen.home

import android.graphics.pdf.PdfDocument.Page
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.alexcao.starpx.model.ImageSet
import com.alexcao.starpx.repository.ImagePagingSource
import com.alexcao.starpx.repository.Repository
import com.alexcao.starpx.repository.Repository.Companion.NETWORK_PAGE_SIZE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: Repository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    val pagingDataFlow: Flow<PagingData<ImageSet>> = Pager(
            PagingConfig(pageSize = NETWORK_PAGE_SIZE)
        ) {
            ImagePagingSource(repository)
        }.flow.cachedIn(viewModelScope)
}