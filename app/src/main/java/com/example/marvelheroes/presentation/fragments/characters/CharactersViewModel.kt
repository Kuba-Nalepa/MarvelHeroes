package com.example.marvelheroes.presentation.fragments.characters

import com.example.marvelheroes.data.model.Character
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelheroes.data.model.ComicBook
import com.example.marvelheroes.domain.usecases.GetCharacterComicsUseCase
import com.example.marvelheroes.domain.usecases.GetCharactersListUseCase
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class CharactersViewModel(
    private val getCharactersListUseCase: GetCharactersListUseCase,
    private val getCharacterComicsUseCase: GetCharacterComicsUseCase
) : ViewModel() {
    private val _characters = MutableLiveData<Map<Character, List<ComicBook>>>()
    val characters = _characters

    init {
        viewModelScope.launch {
            getCharacters()

        }
    }

    private suspend fun getCharacters() {
        getCharactersListUseCase.execute().collect { characters ->
            val map = mutableMapOf<Character, List<ComicBook>>()
            for (character in characters) {
                character.id?.let {
                    map.put(character, getCharacterComics(it))
                }
            }

            _characters.postValue(map)
        }
    }

    private suspend fun getCharacterComics(id: Int): List<ComicBook> {
        var comicList = emptyList<ComicBook>()
        runBlocking {
            getCharacterComicsUseCase.execute(id).collect {
                comicList = it
            }
        }
        return comicList
    }
}