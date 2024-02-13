package com.example.marvelheroes.data.repositoriesImpl

import com.example.marvelheroes.data.model.Character
import com.example.marvelheroes.data.model.MarvelResponse
import com.example.marvelheroes.domain.repositories.CharacterDataSource
import com.example.marvelheroes.data.service.MarvelService

class CharacterDataSourceImpl(
private val marvelService: MarvelService
): CharacterDataSource {
    override suspend fun getCharacters(): MarvelResponse<Character> {
        val response = marvelService.getCharacters()
        if (response.isSuccessful) {
            return response.body() ?: throw IllegalArgumentException("Failed to return Marvel characters")
        }
        else {
            throw RuntimeException(response.errorBody().toString())
        }
    }

    override suspend fun getCharacterDetails(id: Int): MarvelResponse<Character> {
        val response = marvelService.getCharacterDetails(id)
        if (response.isSuccessful) {
            return response.body() ?: throw IllegalArgumentException("Failed to return character details")
        }
        else {
            throw RuntimeException(response.message().toString())
        }
    }
}