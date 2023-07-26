package com.example.marvelheroes.domain.repositories

import com.example.marvelheroes.data.model.Event
import com.example.marvelheroes.data.model.MarvelResponse

interface EventsDataSource {

    suspend fun getEvents(): MarvelResponse<Event>
}