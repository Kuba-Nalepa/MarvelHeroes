package com.example.marvelheroes.presentation.fragments.comics

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelheroes.data.model.ComicBook
import com.example.marvelheroes.domain.usecases.GetComicsUseCase
import kotlinx.coroutines.launch

class ComicsViewModel(
    private val getComicsUseCase: GetComicsUseCase
) : ViewModel() {
    private val _allComics = MutableLiveData<List<ComicBook>>()
    val allComics = _allComics
    private val _homePageComics = MutableLiveData<List<ComicBook>>()
    val homePageCOmics = _homePageComics

    init {
        viewModelScope.launch {
            fetchComicsList()
        }
    }

    private suspend fun fetchComicsList() {
        getComicsUseCase.execute().collect { comicsList ->

            val filteredComicList = comicsList.filter { comicBook ->
                !comicBook.thumbnail.path.endsWith("image_not_available")
            }
            val mainSectionComics = filteredComicList.shuffled().take(3)

            _homePageComics.postValue(mainSectionComics)
            _allComics.postValue(filteredComicList)
        }
    }

}