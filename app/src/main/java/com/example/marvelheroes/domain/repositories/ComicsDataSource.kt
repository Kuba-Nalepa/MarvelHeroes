package com.example.marvelheroes.domain.repositories

import com.example.marvelheroes.data.model.ComicsItem
import com.example.marvelheroes.data.model.MarvelResponse

interface ComicsDataSource {

    suspend fun getComics(): MarvelResponse<ComicsItem>
}