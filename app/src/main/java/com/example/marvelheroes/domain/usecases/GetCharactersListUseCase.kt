package com.example.marvelheroes.domain.usecases

import com.example.marvelheroes.data.model.Character
import com.example.marvelheroes.data.repositoriesImpl.CharacterDataSourceImpl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetCharactersListUseCase(
    private val characterDataSourceImpl: CharacterDataSourceImpl
) {
    suspend fun execute(): Flow<List<Character>> = flow {
         val allCharacters = characterDataSourceImpl.getAllCharacters().marvelData.results
        emit(allCharacters)
    }
}