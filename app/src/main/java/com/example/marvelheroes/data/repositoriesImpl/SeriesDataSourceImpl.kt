package com.example.marvelheroes.data.repositoriesImpl

import com.example.marvelheroes.data.model.MarvelResponse
import com.example.marvelheroes.data.model.Series
import com.example.marvelheroes.data.service.MarvelService
import com.example.marvelheroes.domain.repositories.SeriesDataSource

class SeriesDataSourceImpl(
    private val marvelService: MarvelService
): SeriesDataSource {
    override suspend fun getSeries(): MarvelResponse<Series> {
        val response = marvelService.getAllSeries()
        if (response.isSuccessful) {
            return response.body() ?: throw IllegalArgumentException("Failed to return Marvel characters")
        }
        else {
            throw RuntimeException(response.errorBody().toString())
        }
    }
}