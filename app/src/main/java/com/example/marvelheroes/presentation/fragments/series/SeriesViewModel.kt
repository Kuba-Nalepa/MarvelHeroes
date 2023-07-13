package com.example.marvelheroes.presentation.fragments.series

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelheroes.data.model.Series
import com.example.marvelheroes.domain.usecases.GetSeriesListUseCase
import kotlinx.coroutines.launch

class SeriesViewModel(
    private val getSeriesUseCase: GetSeriesListUseCase
): ViewModel() {

    private val _series = MutableLiveData<List<Series>>()
    val series = _series

    init {
        viewModelScope.launch {
            // filters to get only objects with 100% existing thumbnail
            _series.postValue(getSeriesUseCase.execute().filter {
                it.description != null || it.thumbnail.path != "http://i.annihil.us/u/prod/marvel/i/mg/b/40/image_not_available"
            })
        }
    }
}