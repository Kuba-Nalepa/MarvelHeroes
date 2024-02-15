package com.example.marvelheroes.domain.usecases

import com.example.marvelheroes.data.model.ComicBook
import com.example.marvelheroes.data.repositoriesImpl.CharacterDataSourceImpl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetCharacterComicsUseCase(
    private val characterDataSourceImpl: CharacterDataSourceImpl
) {
    suspend fun execute(id: Int): Flow<List<ComicBook>> = flow {
        val comicsWithImages = characterDataSourceImpl.getCharacterComics(id).marvelData.results.filter { comicBook ->
            !comicBook.thumbnail.path.endsWith("image_not_available")

        }
        emit(comicsWithImages)
    }

}