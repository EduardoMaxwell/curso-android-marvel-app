package br.com.eduardomaxwell.testing.pagingSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import br.com.eduardomaxwell.core.domain.model.Character

class PagingSourceFactory {

    fun create(heroes: List<Character>) = object : PagingSource<Int, Character>() {
        override fun getRefreshKey(state: PagingState<Int, Character>) = 1

        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
            return LoadResult.Page(
                data = heroes,
                prevKey = null,
                nextKey = 20
            )
        }

    }
}