package com.example.marvelheroes.data.repositoriesImpl

import com.example.marvelheroes.data.model.Character
import com.example.marvelheroes.data.model.Creator
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

    override suspend fun getEventDetails(id: Int): MarvelResponse<Event> {
        val response = marvelService.getEventDetails(id.toString())
        if (response.isSuccessful) {

            return response.body() ?: throw IllegalArgumentException("Failed to return Marvel event details")
        }
        else {
            throw RuntimeException(response.errorBody().toString())
        }
    }

    override suspend fun getEventCreators(id: Int): MarvelResponse<Creator> {
        val response = marvelService.getEventCreators(id)
        if (response.isSuccessful) {

            return response.body() ?: throw IllegalArgumentException("Failed to return Marvel event featuringCreators")
        }
        else {
            throw RuntimeException(response.errorBody().toString())
        }
    }

    override suspend fun getCreatorRole(id: Int): MarvelResponse<Event> {
        val response = marvelService.getCreatorRole(id)
        if (response.isSuccessful) {

            return response.body() ?: throw IllegalArgumentException("Failed to return Marvel creator's event")
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