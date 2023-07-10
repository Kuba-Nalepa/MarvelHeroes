package com.example.marvelheroes.domain.usecases

import com.example.marvelheroes.data.model.ComicsItem
import com.example.marvelheroes.data.repositoriesImpl.ComicBooksDataSourceImpl

class GetComicBooksUseCase(
    private val comicBooksDataSourceImpl: ComicBooksDataSourceImpl
) {
    suspend fun execute(): List<ComicsItem> = comicBooksDataSourceImpl.getComicBooks()
}