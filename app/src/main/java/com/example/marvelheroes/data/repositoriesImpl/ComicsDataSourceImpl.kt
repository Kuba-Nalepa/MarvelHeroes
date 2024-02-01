package com.example.marvelheroes.data.repositoriesImpl

import com.example.marvelheroes.data.model.Character
import com.example.marvelheroes.data.model.ComicBook
import com.example.marvelheroes.data.model.Creator
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

    override suspend fun getComicsDetails(id: Int): MarvelResponse<ComicBook> {
        val response = marvelService.getComicDetails(id)
        if (response.isSuccessful) {
            return response.body() ?: throw IllegalArgumentException("Failed to return Marvel comic book details")

        }
        else {
            throw RuntimeException(response.errorBody().toString())
        }
    }

    override suspend fun getComicsCharacters(id: Int): MarvelResponse<Character> {
        val response = marvelService.getComicCharacters(id)
        if (response.isSuccessful) {
            return response.body() ?: throw IllegalArgumentException("Failed to return Marvel comic book characters")

        }
        else {
            throw RuntimeException(response.errorBody().toString())
        }
    }

    override suspend fun getComicsCreators(id: Int): MarvelResponse<Creator> {
        val response = marvelService.getComicCreators(id)
        if (response.isSuccessful) {
            return response.body() ?: throw IllegalArgumentException("Failed to return Marvel comic book creators")

        }
        else {
            throw RuntimeException(response.errorBody().toString())
        }
    }
}