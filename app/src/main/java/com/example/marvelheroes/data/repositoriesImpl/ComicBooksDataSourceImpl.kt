package com.example.marvelheroes.data.repositoriesImpl

import com.example.marvelheroes.data.model.ComicsItem
import com.example.marvelheroes.domain.repositories.ComicBooksDataSource
import com.example.marvelheroes.data.service.MarvelService

class ComicBooksDataSourceImpl(
    private val marvelService: MarvelService
): ComicBooksDataSource {
    override suspend fun getComicBooks(): List<ComicsItem> {
        val response = marvelService.getAllComics()
        if (response.isSuccessful) {
            return response.body() ?: throw IllegalArgumentException("Failed to return Marvel comic books")
        }
        else {
            throw RuntimeException(response.errorBody().toString())
        }
    }
}