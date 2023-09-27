package com.example.marvelheroes.presentation.fragments.comics

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelheroes.data.model.ComicBookItem
import com.example.marvelheroes.domain.usecases.GetComicsUseCase
import kotlinx.coroutines.launch

class ComicsViewModel(
    private val getComicsUseCase: GetComicsUseCase
): ViewModel() {
    private val _comics = MutableLiveData<List<ComicBookItem>>()
    val comics = _comics

    init {
        viewModelScope.launch {
            getComicsUseCase.execute().collect { comicsList ->
                comics.postValue(comicsList)
            }
        }
    }
}