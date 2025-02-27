package com.example.marvelapp.framework

import androidx.paging.PagingSource
import br.com.eduardomaxwell.core.data.repository.CharactersRemoteDataSource
import br.com.eduardomaxwell.core.data.repository.CharactersRepository
import br.com.eduardomaxwell.core.domain.model.Character
import com.example.marvelapp.framework.network.response.DataWrapperResponse
import com.example.marvelapp.framework.paging.CharactersPagingSource
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val remoteDataSource: CharactersRemoteDataSource<DataWrapperResponse>
) : CharactersRepository {

    override fun getCharacters(query: String): PagingSource<Int, Character> {
        return CharactersPagingSource(remoteDataSource, query)
    }
}