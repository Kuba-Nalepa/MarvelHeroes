package com.example.marvelheroes.presentation.fragments.events.eventDetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelheroes.data.model.Character
import com.example.marvelheroes.data.model.Creator
import com.example.marvelheroes.data.model.Event
import com.example.marvelheroes.domain.usecases.GetCharactersEventUseCase
import com.example.marvelheroes.domain.usecases.GetCreatorsRoleUseCase
import com.example.marvelheroes.domain.usecases.GetEventCreatorsUseCase
import com.example.marvelheroes.domain.usecases.GetEventDetailsUseCase
import kotlinx.coroutines.launch

class EventDetailsViewModel(
    private val getEventDetailsUseCase: GetEventDetailsUseCase,
    private val getCharactersEventUseCase: GetCharactersEventUseCase,
    private val getEventCreatorsUseCase: GetEventCreatorsUseCase,
    private val getCreatorsRoleUseCase: GetCreatorsRoleUseCase
) : ViewModel() {

    private val _eventDetails = MutableLiveData<Event>()
    val eventDetails = _eventDetails
    private val _eventCharacters = MutableLiveData<List<Character>>()
    val eventCharacters = _eventCharacters
    private val _eventCreators = MutableLiveData<List<Creator>?>()
    val eventCreators = _eventCreators
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading = _isLoading

    fun getEventDetails(id: Int) {
        viewModelScope.launch {
            getEventDetailsUseCase.execute(id).collect {
                _eventDetails.postValue(it)
            }
        }
    }

    fun getCharactersEvent(id: Int) {
        _isLoading.postValue(true)
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

    fun getEventCreators(eventId: Int, quantity: Int = 5) {
        _isLoading.postValue(true)
        viewModelScope.launch {
            getEventCreatorsUseCase.execute(eventId).collect { result1 ->
                if (result1.isSuccess) {
                    val creator = result1.getOrNull()?.first()

                    creator?.id?.let {
                        getCreatorsRoleUseCase.execute(it, eventId).collect { result2 ->
                            if (result2.isSuccess) {
                                result2.getOrNull().toString()
                                _isLoading.postValue(false)
                                _eventCreators.postValue(result2.getOrNull()?.take(quantity))
                            }
                        }
                    }
                }
            }
        }
    }
    fun getFiveMoreCreators(eventId: Int) {
        _isLoading.postValue(true)
        _eventCreators.value?.size?.let { getEventCreators(eventId, it + 5) }

    }


    fun getEventCurrentImage(): String {
        val currentImagePath = _eventDetails.value?.thumbnail?.path
        val currentImageExtension = _eventDetails.value?.thumbnail?.extension
        return "$currentImagePath.$currentImageExtension".replaceFirst("http", "https")
    }

    fun getFormattedDates(): Pair<String?, String?> {
        val startDate = _eventDetails.value?.startDate?.replace("00:00:00", "")
        val endDate = _eventDetails.value?.endDate?.replace("00:00:00", "")
        return Pair(startDate, endDate)
    }
}