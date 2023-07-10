package com.example.marvelheroes.domain.repositories

import com.example.marvelheroes.data.model.ComicsItem

interface ComicBooksDataSource {

    suspend fun getComicBooks(): List<ComicsItem>
}