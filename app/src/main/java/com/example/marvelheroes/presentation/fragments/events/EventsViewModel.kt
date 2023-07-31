package com.example.marvelheroes.presentation.fragments.events

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelheroes.data.model.Character
import com.example.marvelheroes.data.model.Event
import com.example.marvelheroes.domain.usecases.GetEventCharacterUseCase
import com.example.marvelheroes.domain.usecases.GetEventsListUseCase
import kotlinx.coroutines.launch

class EventsViewModel(
    private val getEventsUseCase: GetEventsListUseCase,
    private val getEventCharactersUseCase: GetEventCharacterUseCase
): ViewModel() {

    private val _mainSectionEvents = MutableLiveData<List<Event>>()
    private val _allEvents = MutableLiveData<List<Event>>()
    private val _eventCharacters = MutableLiveData<List<Character>>()
    fun mainSectionEvents() =_mainSectionEvents as LiveData<List<Event>>

    fun allEvents() = _allEvents as LiveData<List<Event>>


    fun eventCharacters() = _eventCharacters as LiveData<Character>

    init {
        viewModelScope.launch {
//            // filters to get only objects with 100% existing thumbnail
            val mainSectionEvents = getEventsUseCase.execute().shuffled().take(3)
            val allEvents = getEventsUseCase.execute()

            _mainSectionEvents.postValue(mainSectionEvents)
            _allEvents.postValue(allEvents)
             _eventCharacters.postValue(getEventCharactersUseCase.execute(238))
        }
    }
}