package com.example.marvelheroes

import android.app.Application
import com.example.marvelheroes.data.repositoriesImpl.CharacterDataSourceImpl
import com.example.marvelheroes.data.repositoriesImpl.ComicBooksDataSourceImpl
import com.example.marvelheroes.data.service.MarvelService
import com.example.marvelheroes.domain.usecases.GetCharacterDetailsUseCase
import com.example.marvelheroes.domain.usecases.GetCharactersListUseCase
import com.example.marvelheroes.domain.usecases.GetComicBooksUseCase
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
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
        single { ComicBooksDataSourceImpl(get()) }
    }

    private val domainModule = module {
        // Usecases
        single { GetCharacterDetailsUseCase(get()) }
        single { GetCharactersListUseCase(get()) }
        single { GetComicBooksUseCase(get()) }
    }

    private val viewModelModule = module {
//        viewModel {}
    }
}