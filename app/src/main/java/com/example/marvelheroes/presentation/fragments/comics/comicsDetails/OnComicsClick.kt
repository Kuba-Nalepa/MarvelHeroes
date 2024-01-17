package com.example.marvelheroes.presentation.fragments.comics.comicsDetails

import com.example.marvelheroes.data.model.ComicBook

interface OnComicsClick {
    fun onComicsClick(comic: ComicBook, position: Int)
}