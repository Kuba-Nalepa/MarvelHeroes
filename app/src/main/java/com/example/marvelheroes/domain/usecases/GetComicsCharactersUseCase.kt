package com.example.marvelheroes.domain.usecases

import com.example.marvelheroes.data.model.Character
import com.example.marvelheroes.data.repositoriesImpl.ComicsDataSourceImpl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetComicsCharactersUseCase(
    private val comicsDataSourceImpl: ComicsDataSourceImpl
) {
    suspend fun execute(id: Int): Flow<Result<List<Character>>> = flow {
        try {
            val comicsCharacters = comicsDataSourceImpl.getComicsCharacters(id).marvelData.results
            emit(Result.success(comicsCharacters))
        }
        catch (error: IllegalStateException) {
            emit(Result.failure(error))
        }

    }
}