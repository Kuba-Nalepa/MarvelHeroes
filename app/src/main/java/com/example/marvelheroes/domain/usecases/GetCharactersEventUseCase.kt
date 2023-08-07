package com.example.marvelheroes.domain.usecases

import com.example.marvelheroes.data.model.Character
import com.example.marvelheroes.data.repositoriesImpl.EventsDataSourceImpl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetCharactersEventUseCase(
    private val eventsDataSourceImpl: EventsDataSourceImpl
) {
    fun execute(id: Int): Flow<List<Character>> = flow {
        val charactersList = eventsDataSourceImpl.getCharactersEvent(id).marvelData.results
        emit(charactersList)
    }
}