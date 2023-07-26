package com.example.marvelheroes.presentation.fragments.events

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelheroes.data.model.Event
import com.example.marvelheroes.domain.usecases.GetEventsListUseCase
import kotlinx.coroutines.launch

class EventsViewModel(
    private val getSeriesUseCase: GetEventsListUseCase
): ViewModel() {

    private val _series = MutableLiveData<List<Event>>()
    val series = _series

    init {
        viewModelScope.launch {
//            // filters to get only objects with 100% existing thumbnail
            val seriesList = getSeriesUseCase.execute().shuffled().take(3)
            Log.d("viewmodel", seriesList.toString())
            _series.postValue(seriesList)
        }
    }
}