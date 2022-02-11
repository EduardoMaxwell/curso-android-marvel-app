package br.com.eduardomaxwell.core.data.network

import br.com.eduardomaxwell.core.data.network.response.DataWrapperResponse
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface MarvelApi {

    @GET("character")
    suspend fun getCharacters(
        @QueryMap queries: Map<String, String>
    ):DataWrapperResponse
}