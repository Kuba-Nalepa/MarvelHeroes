package com.example.marvelheroes.domain.usecases

import com.example.marvelheroes.data.model.CharacterDetails
import com.example.marvelheroes.data.repositoriesImpl.CharacterDataSourceImpl

class GetCharactersListUseCase(
    private val characterDataSourceImpl: CharacterDataSourceImpl
) {
    suspend fun execute(): List<CharacterDetails> {
        return characterDataSourceImpl.getAllCharacters().marvelData.results
    }
}