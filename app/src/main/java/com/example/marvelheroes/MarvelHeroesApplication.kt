package com.example.marvelheroes

import android.app.Application
import com.example.marvelheroes.data.repositoriesImpl.CharacterDataSourceImpl
import com.example.marvelheroes.data.repositoriesImpl.ComicsDataSourceImpl
import com.example.marvelheroes.data.repositoriesImpl.EventsDataSourceImpl
import com.example.marvelheroes.data.service.MarvelService
import com.example.marvelheroes.domain.usecases.GetCharacterDetailsUseCase
import com.example.marvelheroes.domain.usecases.GetCharactersListUseCase
import com.example.marvelheroes.domain.usecases.GetComicsUseCase
import com.example.marvelheroes.domain.usecases.GetEventsListUseCase
import com.example.marvelheroes.presentation.fragments.characters.CharactersViewModel
import com.example.marvelheroes.presentation.fragments.comics.ComicsViewModel
import com.example.marvelheroes.presentation.fragments.events.EventsViewModel
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
        // Usecases
        single { GetCharacterDetailsUseCase(get()) }
        single { GetCharactersListUseCase(get()) }
        single { GetComicsUseCase(get()) }
        single { GetEventsListUseCase(get()) }
    }

    private val viewModelModule = module {
        viewModel { CharactersViewModel(get()) }
        viewModel { ComicsViewModel(get()) }
        viewModel { EventsViewModel(get()) }
    }
}