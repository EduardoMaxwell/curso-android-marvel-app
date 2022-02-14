package com.example.marvelapp.presentation.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import br.com.eduardomaxwell.core.domain.model.Character
import br.com.eduardomaxwell.core.usecase.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase
) : ViewModel() {

    fun charactersPagingDataSource(query: String): Flow<PagingData<Character>> {
        return getCharactersUseCase(
            GetCharactersUseCase.GetCharactersParams(query, getPagingConfig())
        ).cachedIn(viewModelScope)
    }

    private fun getPagingConfig() = PagingConfig(
        pageSize = 20
    )
}