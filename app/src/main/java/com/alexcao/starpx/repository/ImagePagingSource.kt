package com.alexcao.starpx.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.alexcao.starpx.model.ImageSet
import com.alexcao.starpx.repository.Repository.Companion.NETWORK_PAGE_SIZE

private const val STARTING_PAGE_INDEX = 1

class ImagePagingSource(
    private val repository: Repository,
) : PagingSource<Int, ImageSet>() {
    override fun getRefreshKey(state: PagingState<Int, ImageSet>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ImageSet> {
        return try {
            val position = params.key ?: STARTING_PAGE_INDEX
            val images = repository.getImages(repository.getNextToken(), params.loadSize)
            val nextKey = if (repository.getNextToken() == null) {
                null
            } else {
                position + (params.loadSize / NETWORK_PAGE_SIZE)
            }

            LoadResult.Page(
                data = images,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}