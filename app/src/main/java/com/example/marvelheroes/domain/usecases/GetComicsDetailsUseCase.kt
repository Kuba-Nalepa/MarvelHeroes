package com.example.marvelheroes.domain.usecases

import com.example.marvelheroes.data.model.ComicBook
import com.example.marvelheroes.data.repositoriesImpl.ComicsDataSourceImpl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetComicsDetailsUseCase(
    private val comicsDataSourceImpl: ComicsDataSourceImpl
) {
    suspend fun execute(id: Int): Flow<ComicBook> = flow {
        comicsDataSourceImpl.getComicsDetails(id).marvelData.results.forEach { comicBook ->
        emit(comicBook)
        }
    }
}