package com.example.marvelheroes.presentation.viewmodels

import com.example.marvelheroes.data.model.CharacterDetails
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelheroes.domain.usecases.GetCharactersListUseCase
import kotlinx.coroutines.launch


class CharactersViewModel(
    private val getCharactersListUseCase: GetCharactersListUseCase
) : ViewModel() {
    private val _characters = MutableLiveData<List<CharacterDetails>>()
    val characters = _characters

    init {
        viewModelScope.launch {
            _characters.postValue(getCharactersListUseCase.execute())
        }
    }
}