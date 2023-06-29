package com.example.marvelheroes.domain.usecases

import com.example.marvelheroes.data.repositoriesImpl.CharacterDataSourceImpl

class GetCharacterDetailsUseCase(
    private val characterDataSourceImpl: CharacterDataSourceImpl
) {
    suspend fun execute(id: Int) = characterDataSourceImpl.getCharacterDetails(id)
}