package com.example.marvelheroes.domain.usecases

import com.example.marvelheroes.data.model.Character
import com.example.marvelheroes.data.repositoriesImpl.EventsDataSourceImpl

class GetEventCharacterUseCase(
    private val eventsDataSourceImpl: EventsDataSourceImpl
) {
    suspend fun execute(id: Int): List<Character> = eventsDataSourceImpl.getCharactersEvent(id).marvelData.results
}