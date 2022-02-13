package br.com.eduardomaxwell.core.data.repository

import androidx.paging.PagingSource
import br.com.eduardomaxwell.core.domain.model.Character

interface CharactersRepository {

    fun getCharacters(query: String): PagingSource<Int, Character>
}