package com.example.marvelheroes.domain.usecases

import com.example.marvelheroes.data.model.Creator
import com.example.marvelheroes.data.repositoriesImpl.ComicsDataSourceImpl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetComicsCreatorsUseCase(
    private val comicsDataSourceImpl: ComicsDataSourceImpl
) {
    suspend fun execute(id: Int) : Flow<Result<List<Creator>>> = flow {
        try {
            val creators = comicsDataSourceImpl.getComicsCreators(id).marvelData.results
            emit(Result.success(creators))

        }
        catch (error: IllegalStateException) {
            emit(Result.failure(error))
        }


    }
}