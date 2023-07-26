package com.example.marvelheroes.domain.usecases

import com.example.marvelheroes.data.model.Event
import com.example.marvelheroes.data.repositoriesImpl.EventsDataSourceImpl

class GetEventsListUseCase(
    private val seriesDataSourceImpl: EventsDataSourceImpl
) {
    suspend fun execute(): List<Event> = seriesDataSourceImpl.getEvents().marvelData.results
}