package com.example.marvelheroes.domain.repositories

import com.example.marvelheroes.data.model.CharacterDetails
import com.example.marvelheroes.data.model.MarvelResponse

interface CharacterDataSource {

    suspend fun getAllCharacters(): MarvelResponse<CharacterDetails>

    suspend fun getCharacterDetails(id: Int): CharacterDetails

}