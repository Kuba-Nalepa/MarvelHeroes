package com.example.marvelheroes.domain.usecases

import com.example.marvelheroes.data.repositoriesImpl.ComicBooksDataSourceImpl
import com.example.marvelheroes.domain.model.ComicBook

class GetComicBooksUseCase(
    private val comicBooksDataSourceImpl: ComicBooksDataSourceImpl
) {
    suspend fun execute(): List<ComicBook> = comicBooksDataSourceImpl.getComicBooks()
}