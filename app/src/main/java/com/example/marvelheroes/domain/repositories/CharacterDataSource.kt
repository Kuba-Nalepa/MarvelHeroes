package com.example.marvelheroes.domain.repositories

import com.example.marvelheroes.domain.model.Character
import com.example.marvelheroes.data.model.CharacterDetails

interface CharacterDataSource {

    suspend fun getAllCharacters(): List<Character>

    suspend fun getCharacterDetails(id: Int): CharacterDetails

}