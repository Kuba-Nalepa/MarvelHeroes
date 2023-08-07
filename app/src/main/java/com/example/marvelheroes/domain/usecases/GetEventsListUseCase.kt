package com.example.marvelheroes.domain.usecases

import com.example.marvelheroes.data.model.Event
import com.example.marvelheroes.data.repositoriesImpl.EventsDataSourceImpl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetEventsListUseCase(
    private val seriesDataSourceImpl: EventsDataSourceImpl
) {
    suspend fun execute(): Flow<List<Event>> = flow {
        val eventsList = seriesDataSourceImpl.getEvents().marvelData.results
        emit(eventsList)
    }
}
