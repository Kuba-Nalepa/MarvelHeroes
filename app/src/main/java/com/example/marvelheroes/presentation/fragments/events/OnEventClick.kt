package com.example.marvelheroes.presentation.fragments.events

import com.example.marvelheroes.data.model.Event

interface OnEventClick {
    fun onEventClick(event: Event, position: Int)
}