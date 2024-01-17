package com.example.marvelheroes.presentation.fragments.comics

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelheroes.data.model.ComicBook
import com.example.marvelheroes.domain.usecases.GetComicsUseCase
import kotlinx.coroutines.launch

class ComicsViewModel(
    private val getComicsUseCase: GetComicsUseCase
): ViewModel() {
    private val _allComics = MutableLiveData<List<ComicBook>>()
    private val _homePageComics = MutableLiveData<List<ComicBook>>()

    fun homePageComics() =_homePageComics as LiveData<List<ComicBook>>

    fun allComics() = _allComics as LiveData<List<ComicBook>>

    init {
        viewModelScope.launch {
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

}