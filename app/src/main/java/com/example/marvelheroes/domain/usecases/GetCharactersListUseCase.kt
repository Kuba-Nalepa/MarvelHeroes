package com.example.marvelheroes.domain.usecases

import com.example.marvelheroes.data.repositoriesImpl.CharacterDataSourceImpl
import com.example.marvelheroes.domain.model.Character

class GetCharactersListUseCase(
    private val characterDataSourceImpl: CharacterDataSourceImpl
) {
    suspend fun execute(): List<Character> = characterDataSourceImpl.getAllCharacters()
}