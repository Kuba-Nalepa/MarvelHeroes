package com.example.marvelheroes.presentation.fragments.events

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelheroes.data.model.Event
import com.example.marvelheroes.domain.usecases.GetEventsListUseCase
import kotlinx.coroutines.launch

class EventsViewModel(
    private val getEventsUseCase: GetEventsListUseCase,
): ViewModel() {

    private val _mainSectionEvents = MutableLiveData<List<Event>>()
    val mainSectionEvents = _mainSectionEvents
    private val _allEvents = MutableLiveData<List<Event>>()
    val allEvents = _allEvents

    init {
        viewModelScope.launch {
            getAllEvents()
        }
    }

    private suspend fun getAllEvents() {
        getEventsUseCase.execute().collect{ eventsList ->
            // retrieves three events that are displayed in main section
            val homePageEvents = eventsList.shuffled().take(3)
            _mainSectionEvents.postValue(homePageEvents)
            // retrieves all events
            _allEvents.postValue(eventsList)
        }
    }
}