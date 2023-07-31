package com.example.marvelheroes.data.repositoriesImpl

import com.example.marvelheroes.data.model.Character
import com.example.marvelheroes.data.model.Event
import com.example.marvelheroes.data.model.MarvelResponse
import com.example.marvelheroes.data.service.MarvelService
import com.example.marvelheroes.domain.repositories.EventsDataSource

class EventsDataSourceImpl(
    private val marvelService: MarvelService
): EventsDataSource {
    override suspend fun getEvents(): MarvelResponse<Event> {
        val response = marvelService.getAllEvents()
        if (response.isSuccessful) {
            return response.body() ?: throw IllegalArgumentException("Failed to return Marvel events")
        }
        else {
            throw RuntimeException(response.errorBody().toString())
        }
    }

    override suspend fun getCharactersEvent(id: Int): MarvelResponse<Character> {
        val response = marvelService.getCharactersAtEvent(id)
        if (response.isSuccessful) {
            return response.body() ?: throw IllegalArgumentException("Failed to return Marvel event characters")
        }
        else {
            throw RuntimeException(response.errorBody().toString())
        }
    }
}