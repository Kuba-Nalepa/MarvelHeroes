package com.example.marvelheroes.domain.usecases

import com.example.marvelheroes.data.model.Series
import com.example.marvelheroes.data.repositoriesImpl.SeriesDataSourceImpl

class GetSeriesListUseCase(
    private val seriesDataSourceImpl: SeriesDataSourceImpl
) {
    suspend fun execute(): List<Series> = seriesDataSourceImpl.getSeries().marvelData.results
}