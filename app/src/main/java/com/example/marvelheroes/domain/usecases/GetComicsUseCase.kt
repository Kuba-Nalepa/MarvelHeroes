package com.example.marvelheroes.domain.usecases

import com.example.marvelheroes.data.model.ComicBookItem
import com.example.marvelheroes.data.repositoriesImpl.ComicsDataSourceImpl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetComicsUseCase(
    private val comicsDataSourceImpl: ComicsDataSourceImpl
) {
    suspend fun execute(): Flow<List<ComicBookItem>> = flow {
        val comicsList = comicsDataSourceImpl.getComics().marvelData.results
        emit(comicsList)
    }
}