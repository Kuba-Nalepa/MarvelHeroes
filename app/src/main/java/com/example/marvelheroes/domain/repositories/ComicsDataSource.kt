package com.example.marvelheroes.domain.repositories

import com.example.marvelheroes.data.model.Character
import com.example.marvelheroes.data.model.ComicBook
import com.example.marvelheroes.data.model.MarvelResponse

interface ComicsDataSource {

    suspend fun getComics(): MarvelResponse<ComicBook>

    suspend fun getComicsDetails(id: Int): MarvelResponse<ComicBook>

    suspend fun getComicsCharacters(id: Int): MarvelResponse<Character>
}