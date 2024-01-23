package com.example.marvelheroes.domain.usecases

import com.example.marvelheroes.data.model.Character
import com.example.marvelheroes.data.repositoriesImpl.ComicsDataSourceImpl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetComicsCharactersUseCase(
    private val comicsDataSourceImpl: ComicsDataSourceImpl
) {
    suspend fun execute(id: Int): Flow<List<Character>> = flow {
        val comicsCharacters = comicsDataSourceImpl.getComicsCharacters(id).marvelData.results
        emit(comicsCharacters)
    }
}