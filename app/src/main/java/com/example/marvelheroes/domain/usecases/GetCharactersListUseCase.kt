package com.example.marvelheroes.domain.usecases

import com.example.marvelheroes.data.model.Character
import com.example.marvelheroes.data.repositoriesImpl.CharacterDataSourceImpl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetCharactersListUseCase(
    private val characterDataSourceImpl: CharacterDataSourceImpl
) {
    suspend fun execute(): Flow<List<Character>> = flow {
         val allCharacters = characterDataSourceImpl.getCharacters().marvelData.results
        // filters characters with description and image at least
        val filteredCharacters = allCharacters.filter { character ->
            character.thumbnail?.path?.endsWith("image_not_available") == false && character.description != ""

        }

        emit(filteredCharacters)
    }
}