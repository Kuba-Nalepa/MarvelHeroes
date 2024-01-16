package com.example.marvelheroes.domain.repositories

import com.example.marvelheroes.data.model.ComicBook
import com.example.marvelheroes.data.model.ComicBookItem
import com.example.marvelheroes.data.model.MarvelResponse

interface ComicsDataSource {

    suspend fun getComics(): MarvelResponse<ComicBook>
}