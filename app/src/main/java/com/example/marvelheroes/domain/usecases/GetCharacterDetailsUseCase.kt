package com.example.marvelheroes.domain.usecases

import com.example.marvelheroes.data.model.Character
import com.example.marvelheroes.data.repositoriesImpl.CharacterDataSourceImpl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetCharacterDetailsUseCase(
    private val characterDataSourceImpl: CharacterDataSourceImpl
) {
    suspend fun execute(id: Int) : Flow<Character> = flow {
         characterDataSourceImpl.getCharacterDetails(id).marvelData.results.forEach { character ->
             emit(character)
         }
    }
}