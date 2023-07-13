package com.example.marvelheroes.domain.repositories

import com.example.marvelheroes.data.model.Character
import com.example.marvelheroes.data.model.MarvelResponse

interface CharacterDataSource {

    suspend fun getAllCharacters(): MarvelResponse<Character>

    suspend fun getCharacterDetails(id: Int): MarvelResponse<Character>

}