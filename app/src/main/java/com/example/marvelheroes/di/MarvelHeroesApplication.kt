package com.example.marvelheroes.di

import android.app.Application
import com.example.marvelheroes.data.repositoriesImpl.CharacterDataSourceImpl
import com.example.marvelheroes.data.repositoriesImpl.ComicsDataSourceImpl
import com.example.marvelheroes.data.repositoriesImpl.EventsDataSourceImpl
import com.example.marvelheroes.data.service.MarvelService
import com.example.marvelheroes.domain.usecases.GetCharacterComicsUseCase
import com.example.marvelheroes.domain.usecases.GetCharacterDetailsUseCase
import com.example.marvelheroes.domain.usecases.GetCharactersListUseCase
import com.example.marvelheroes.domain.usecases.GetComicsUseCase
import com.example.marvelheroes.domain.usecases.GetCharactersEventUseCase
import com.example.marvelheroes.domain.usecases.GetComicsCharactersUseCase
import com.example.marvelheroes.domain.usecases.GetComicsCreatorsUseCase
import com.example.marvelheroes.domain.usecases.GetComicsDetailsUseCase
import com.example.marvelheroes.domain.usecases.GetCreatorsRoleUseCase
import com.example.marvelheroes.domain.usecases.GetEventCreatorsUseCase
import com.example.marvelheroes.domain.usecases.GetEventDetailsUseCase
import com.example.marvelheroes.domain.usecases.GetEventsListUseCase
import com.example.marvelheroes.presentation.fragments.characters.CharactersViewModel
import com.example.marvelheroes.presentation.fragments.comics.ComicsViewModel
import com.example.marvelheroes.presentation.fragments.comics.comicsDetails.ComicsDetailsViewModel
import com.example.marvelheroes.presentation.fragments.events.EventsViewModel
import com.example.marvelheroes.presentation.fragments.events.eventDetails.EventDetailsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MarvelHeroesApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MarvelHeroesApplication)
            modules(listOf(dataModule, domainModule, viewModelModule))
        }
    }


    private val dataModule = module {
        // Network
        single { MarvelService.getInstance() }

        // Repositories Implementation
        single { CharacterDataSourceImpl(get()) }
        single { ComicsDataSourceImpl(get()) }
        single { EventsDataSourceImpl(get()) }
    }

    private val domainModule = module {
        // Use cases
        single { GetCharacterDetailsUseCase(get()) }
        single { GetCharactersListUseCase(get()) }
        single { GetCharactersEventUseCase(get()) }
        single { GetCharacterComicsUseCase(get()) }
        single { GetComicsUseCase(get()) }
        single { GetComicsDetailsUseCase(get()) }
        single { GetComicsCharactersUseCase(get()) }
        single { GetComicsCreatorsUseCase(get()) }
        single { GetEventsListUseCase(get()) }
        single { GetEventDetailsUseCase(get()) }
        single { GetEventCreatorsUseCase(get()) }
        single { GetCreatorsRoleUseCase(get()) }
    }

    private val viewModelModule = module {
        viewModel { CharactersViewModel(get(), get()) }
        viewModel { ComicsViewModel(get()) }
        viewModel { ComicsDetailsViewModel(get(), get(), get()) }
        viewModel { EventsViewModel(get()) }
        viewModel { EventDetailsViewModel(get(), get(), get(), get()) }
    }
}