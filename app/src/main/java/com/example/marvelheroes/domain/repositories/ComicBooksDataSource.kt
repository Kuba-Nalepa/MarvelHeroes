package com.example.marvelheroes.domain.repositories

import com.example.marvelheroes.domain.model.ComicBook

interface ComicBooksDataSource {

    suspend fun getComicBooks(): List<ComicBook>
}