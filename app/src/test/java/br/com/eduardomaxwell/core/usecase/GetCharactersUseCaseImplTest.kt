package br.com.eduardomaxwell.core.usecase

import androidx.paging.PagingConfig
import br.com.eduardomaxwell.core.data.repository.CharactersRepository
import br.com.eduardomaxwell.testing.MainCoroutineRule
import br.com.eduardomaxwell.testing.model.CharacterFactory
import br.com.eduardomaxwell.testing.pagingSource.PagingSourceFactory
import com.example.marvelapp.framework.CharactersRepositoryImpl
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetCharactersUseCaseImplTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    //    Object mocked
    @Mock
    lateinit var charactersRepository: CharactersRepository

    //    First declare the object will be testing
    private lateinit var getCharactersUseCase: GetCharactersUseCase

    private val hero = CharacterFactory().create(CharacterFactory.Hero.ThreeDMan)
    private val fakePagingSource = PagingSourceFactory().create(listOf(hero))

    @Before
    fun setup() {
        getCharactersUseCase = GetCharactersUseCaseImpl(charactersRepository)
    }

    //    @runBlocking is used when working with @coroutines
    @ExperimentalCoroutinesApi
    @Test
    fun `should validate flow paging data creation when invoke from use case is called`() =
        runBlockingTest {
            whenever(charactersRepository.getCharacters(""))
                .thenReturn(fakePagingSource)

            val result = getCharactersUseCase.invoke(
                GetCharactersUseCase.GetCharactersParams(
                    "",
                    PagingConfig(20)
                )
            )

            verify(charactersRepository).getCharacters("")

            assertNotNull(result.first())
        }
}























