package com.example.marvelheroes.domain.repositories

import com.example.marvelheroes.data.model.MarvelResponse
import com.example.marvelheroes.data.model.Series

interface SeriesDataSource {

    suspend fun getSeries(): MarvelResponse<Series>
}