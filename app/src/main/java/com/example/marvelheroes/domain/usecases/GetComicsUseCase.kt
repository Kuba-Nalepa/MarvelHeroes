package com.example.marvelheroes.domain.usecases

import com.example.marvelheroes.data.model.ComicsItem
import com.example.marvelheroes.data.repositoriesImpl.ComicsDataSourceImpl

class GetComicsUseCase(
    private val comicsDataSourceImpl: ComicsDataSourceImpl
) {
    suspend fun execute(): List<ComicsItem> = comicsDataSourceImpl.getComics().marvelData.results
}