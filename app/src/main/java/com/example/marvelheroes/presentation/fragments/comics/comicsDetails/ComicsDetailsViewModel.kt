package com.example.marvelheroes.presentation.fragments.comics.comicsDetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelheroes.data.model.Character
import com.example.marvelheroes.data.model.ComicBook
import com.example.marvelheroes.data.model.Creator
import com.example.marvelheroes.domain.usecases.GetComicsCharactersUseCase
import com.example.marvelheroes.domain.usecases.GetComicsCreatorsUseCase
import com.example.marvelheroes.domain.usecases.GetComicsDetailsUseCase
import kotlinx.coroutines.launch

class ComicsDetailsViewModel(
    private val getComicsDetailsUseCase: GetComicsDetailsUseCase,
    private val getComicsCharacters: GetComicsCharactersUseCase,
    private val getComicsCreatorsUseCase: GetComicsCreatorsUseCase
): ViewModel() {

    private val _comicsDetails = MutableLiveData<ComicBook>()
    val comicsDetails = _comicsDetails
    private val _comicCharacters = MutableLiveData<List<Character>?>()
    val comicCharacters = _comicCharacters
    private val _comicCreators = MutableLiveData<List<Creator>?>()
    val comicCreators = _comicCreators
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading = _isLoading


    fun getComicsDetails(id: Int) {
        viewModelScope.launch {
            getComicsDetailsUseCase.execute(id).collect() {
                _comicsDetails.postValue(it)
            }
        }
    }

    fun getComicsCurrentImage(): String {
        val currentImagePath = _comicsDetails.value?.thumbnail?.path
        val currentImageExtension = _comicsDetails.value?.thumbnail?.extension
        return "$currentImagePath.$currentImageExtension".replaceFirst("http", "https")
    }

    fun getComicCharacters(id: Int) {
        _isLoading.postValue(true)
        viewModelScope.launch {
            getComicsCharacters.execute(id).collect { result ->
                if(result.isSuccess) {
                    // retrieves only 100% existing images
                    val filteredCharacters = result.getOrNull()?.filter { character ->
                        character.thumbnail?.path?.endsWith("image_not_available") == false
                    }

                    _isLoading.postValue(false)
                    _comicCharacters.postValue(filteredCharacters)
                }
            }
        }
    }

    fun getComicsCreators(id: Int) {
        _isLoading.postValue(true)
        viewModelScope.launch {
            getComicsCreatorsUseCase.execute(id).collect() { result ->
                if(result.isSuccess) {
                    val creators = result.getOrNull()
                    _isLoading.postValue(false)
                    _comicCreators.postValue(creators)
                }
            }
        }
    }
}