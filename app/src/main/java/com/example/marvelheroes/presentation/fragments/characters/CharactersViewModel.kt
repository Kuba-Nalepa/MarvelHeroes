package com.example.marvelheroes.presentation.fragments.characters

import com.example.marvelheroes.data.model.Character
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelheroes.domain.usecases.GetCharactersListUseCase
import kotlinx.coroutines.launch


class CharactersViewModel(
    private val getCharactersListUseCase: GetCharactersListUseCase
) : ViewModel() {
    private val _characters = MutableLiveData<List<Character>>()
    val characters = _characters

    init {
        viewModelScope.launch {
            _characters.postValue(getCharactersListUseCase.execute())
        }
    }
}