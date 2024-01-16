package com.example.marvelheroes.data.repositoriesImpl

import com.example.marvelheroes.data.model.ComicBook
import com.example.marvelheroes.data.model.ComicBookItem
import com.example.marvelheroes.data.model.MarvelResponse
import com.example.marvelheroes.domain.repositories.ComicsDataSource
import com.example.marvelheroes.data.service.MarvelService

class ComicsDataSourceImpl(
    private val marvelService: MarvelService
): ComicsDataSource {
    override suspend fun getComics(): MarvelResponse<ComicBook> {
        val response = marvelService.getAllComics()
        if (response.isSuccessful) {
            return response.body() ?: throw IllegalArgumentException("Failed to return Marvel comic books")
        }
        else {
            throw RuntimeException(response.errorBody().toString())
        }
    }
}