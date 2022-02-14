package br.com.eduardomaxwell.core.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import br.com.eduardomaxwell.core.data.repository.CharactersRepository
import br.com.eduardomaxwell.core.domain.model.Character
import br.com.eduardomaxwell.core.usecase.base.PagingUseCase
import br.com.eduardomaxwell.core.usecase.base.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(
    private val charactersRepository: CharactersRepository
) : PagingUseCase<GetCharactersUseCase.GetCharactersParams, Character>() {

    override fun createFlowObservable(params: GetCharactersParams): Flow<PagingData<Character>> {
        return Pager(config = params.pagingConfig) {
            charactersRepository.getCharacters(params.query)
        }.flow
    }

    data class GetCharactersParams(
        val query: String,
        val pagingConfig: PagingConfig
    )

}