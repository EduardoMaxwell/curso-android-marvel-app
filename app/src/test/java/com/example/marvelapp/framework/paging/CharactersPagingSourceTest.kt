package com.example.marvelapp.framework.paging

import androidx.paging.PagingSource
import br.com.eduardomaxwell.core.data.repository.CharactersRemoteDataSource
import br.com.eduardomaxwell.core.domain.model.Character
import br.com.eduardomaxwell.testing.MainCoroutineRule
import br.com.eduardomaxwell.testing.model.CharacterFactory
import com.example.marvelapp.factory.response.DataWrapperResponseFactory
import com.example.marvelapp.framework.network.response.DataWrapperResponse
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CharactersPagingSourceTest {


    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    lateinit var remoteDataSource: CharactersRemoteDataSource<DataWrapperResponse>

    private val dataWrapperResponseFactory = DataWrapperResponseFactory()

    private val characterFactory = CharacterFactory()

    private lateinit var charactersPagingSource: CharactersPagingSource

    @Before
    fun setup() {
        charactersPagingSource = CharactersPagingSource(remoteDataSource, "")
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `should return success load result when load is called`() = runBlockingTest {
        //Arrange
        whenever(remoteDataSource.fetchCharacters(any()))
            .thenReturn(dataWrapperResponseFactory.create())

        //Act
        val result = charactersPagingSource.load(
            PagingSource.LoadParams.Refresh(
                null,
                2,
                false
            )
        )

        //Assert
        val expected = listOf(
            characterFactory.create(CharacterFactory.Hero.ThreeDMan),
            characterFactory.create(CharacterFactory.Hero.ABomb)
        )
        assertEquals(
            PagingSource.LoadResult.Page(
                data = expected,
                prevKey = null,
                nextKey = 20
            ),
            result
        )
    }


    @ExperimentalCoroutinesApi
    @Test
    fun `should return an error load result when load is called`() = runBlockingTest {
        val exception = RuntimeException(

        )
        //Arrange
        whenever(remoteDataSource.fetchCharacters(any()))
            .thenThrow(exception)

        //Act
        val result = charactersPagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 2,
                placeholdersEnabled = false
            )
        )

        //Assert
        assertEquals(
            PagingSource.LoadResult.Error<Int, Character>(exception),
            result
        )
    }
}































