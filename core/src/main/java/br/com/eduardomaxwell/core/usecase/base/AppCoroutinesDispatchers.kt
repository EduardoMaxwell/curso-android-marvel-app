package br.com.eduardomaxwell.core.usecase.base

import kotlinx.coroutines.CoroutineDispatcher

data class AppCoroutinesDispatchers(
    val io : CoroutineDispatcher,
    val computation: CoroutineDispatcher,
    val main : CoroutineDispatcher
)
