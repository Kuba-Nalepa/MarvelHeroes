package com.example.marvelheroes.domain.usecases

import com.example.marvelheroes.data.model.Event
import com.example.marvelheroes.data.repositoriesImpl.EventsDataSourceImpl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetEventDetailsUseCase(
    private val eventsDataSourceImpl: EventsDataSourceImpl
) {
    suspend fun execute(id: Int): Flow<Event> = flow {
        eventsDataSourceImpl.getEventDetails(id).marvelData.results.forEach {  event ->
            emit(event)
        }
    }
}