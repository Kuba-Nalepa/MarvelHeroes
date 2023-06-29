package com.example.marvelheroes.data.repositoriesImpl

import com.example.marvelheroes.domain.repositories.CharacterDataSource
import com.example.marvelheroes.domain.model.Character
import com.example.marvelheroes.data.model.CharacterDetails
import com.example.marvelheroes.data.service.MarvelService

class CharacterDataSourceImpl(
private val marvelService: MarvelService
): CharacterDataSource {
    override suspend fun getAllCharacters(): List<Character> {
        val response = marvelService.getAllCharacters()
        if (response.isSuccessful) {
            return response.body() ?: throw IllegalArgumentException("Failed to return Marvel characters")
        }
        else {
            throw RuntimeException(response.errorBody().toString())
        }
    }

    override suspend fun getCharacterDetails(id: Int): CharacterDetails {
        val response = marvelService.getCharacterDetails(id)
        if (response.isSuccessful) {
            return response.body() ?: throw IllegalArgumentException("Failed to return character details")
        }
        else {
            throw RuntimeException(response.errorBody().toString())
        }
    }
}