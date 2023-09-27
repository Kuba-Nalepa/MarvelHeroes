package com.example.marvelheroes.domain.usecases

import com.example.marvelheroes.data.model.Creator
import com.example.marvelheroes.data.repositoriesImpl.EventsDataSourceImpl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetEventCreatorsUseCase(
    private val eventsDataSourceImpl: EventsDataSourceImpl
) {

    fun execute(id: Int): Flow<Result<List<Creator>>> = flow {
        try {
            val creators = eventsDataSourceImpl.getEventCreators(id).marvelData.results
            emit(Result.success(creators))

        }
        catch (error: IllegalStateException) {
            emit(Result.failure(error))
        }

    }
}
