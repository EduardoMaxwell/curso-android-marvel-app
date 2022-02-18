package br.com.eduardomaxwell.core.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import br.com.eduardomaxwell.core.data.repository.CharactersRepository
import br.com.eduardomaxwell.core.domain.model.Character
import br.com.eduardomaxwell.core.usecase.GetCharactersUseCase.GetCharactersParams
import br.com.eduardomaxwell.core.usecase.base.PagingUseCase
import br.com.eduardomaxwell.core.usecase.base.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetCharactersUseCase {

    operator fun invoke(params: GetCharactersParams): Flow<PagingData<Character>>

    data class GetCharactersParams(
        val query: String,
        val pagingConfig: PagingConfig
    )

}

class GetCharactersUseCaseImpl @Inject constructor(
    private val charactersRepository: CharactersRepository
) : PagingUseCase<GetCharactersParams, Character>(), GetCharactersUseCase {

    override fun createFlowObservable(params: GetCharactersParams): Flow<PagingData<Character>> {
        val pagingSource = charactersRepository.getCharacters(params.query)
        return Pager(config = params.pagingConfig) {
            pagingSource
        }.flow
    }


}