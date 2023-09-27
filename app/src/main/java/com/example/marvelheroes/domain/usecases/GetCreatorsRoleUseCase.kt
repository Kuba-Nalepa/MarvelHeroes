package com.example.marvelheroes.domain.usecases

import com.example.marvelheroes.data.model.Creator
import com.example.marvelheroes.data.repositoriesImpl.EventsDataSourceImpl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetCreatorsRoleUseCase(
    private val eventsDataSourceImpl: EventsDataSourceImpl
) {
    fun execute(creatorId: Int, eventId: Int): Flow<Result<List<Creator>?>> = flow {
        try {
            val event = eventsDataSourceImpl.getCreatorRole(creatorId).marvelData.results.find {
                it.id == eventId
            }

            emit(Result.success(event?.featuringCreators?.items))

        }
        catch (error: IllegalStateException) {
            emit(Result.failure(error))
        }
    }
}