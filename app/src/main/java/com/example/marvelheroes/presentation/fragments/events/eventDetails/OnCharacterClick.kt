package com.example.marvelheroes.presentation.fragments.events.eventDetails

import com.example.marvelheroes.data.model.Character

interface OnCharacterClick {

    fun onCharacterCLick(character: Character, position: Int)
}