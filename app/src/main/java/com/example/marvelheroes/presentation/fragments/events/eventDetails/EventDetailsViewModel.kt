package com.example.marvelheroes.presentation.fragments.events.eventDetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelheroes.data.model.Character
import com.example.marvelheroes.data.model.Event
import com.example.marvelheroes.domain.usecases.GetCharactersEventUseCase
import com.example.marvelheroes.domain.usecases.GetEventDetailsUseCase
import kotlinx.coroutines.launch

class EventDetailsViewModel(
    private val getEventDetailsUseCase: GetEventDetailsUseCase,
    private val getCharactersEventUseCase: GetCharactersEventUseCase
): ViewModel() {

    private val _eventDetails = MutableLiveData<Event>()
    val eventDetails = _eventDetails
    private val _eventCharacters = MutableLiveData<List<Character>>()
    val eventCharacters = _eventCharacters

    fun getEventDetails(id: Int)  {
    viewModelScope.launch {
        getEventDetailsUseCase.execute(id).collect {
            _eventDetails.postValue(it)
            }
        }
    }

    fun getCharactersEvent(id: Int) {
        viewModelScope.launch {
            getCharactersEventUseCase.execute(id).collect {
                // retrieves only 100% existing images
                val filteredCharacters = it.filter { character ->
                    character.thumbnail?.path?.endsWith("image_not_available") == false
                }
                _eventCharacters.postValue(filteredCharacters)
            }
        }
    }

    fun getEventCurrentImage(): String {
        val currentImagePath = _eventDetails.value?.thumbnail?.path
        val currentImageExtension = _eventDetails.value?.thumbnail?.extension
        return "$currentImagePath.$currentImageExtension".replaceFirst("http", "https")
    }

    fun getFormattedDates(): Pair<String?,String?> {
        val startDate = _eventDetails.value?.startDate?.replace("00:00:00", "")
        val endDate = _eventDetails.value?.endDate?.replace("00:00:00", "")
        return Pair(startDate,endDate)
    }
}