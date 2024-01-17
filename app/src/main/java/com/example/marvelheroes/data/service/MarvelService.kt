package com.example.marvelheroes.data.service

import com.example.marvelheroes.data.model.MarvelResponse
import com.example.marvelheroes.data.model.Character
import com.example.marvelheroes.BuildConfig
import com.example.marvelheroes.data.model.ComicBook
import com.example.marvelheroes.data.model.Creator
import com.example.marvelheroes.data.model.Event
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface MarvelService {

    @GET("characters?ts=1&apikey=${BuildConfig.PUBLIC_API}&hash=${BuildConfig.MD5_HASH}")
    suspend fun getAllCharacters(): Response<MarvelResponse<Character>>

    @GET("characters/{characterId}?ts=1&apikey=${BuildConfig.PUBLIC_API}&hash=${BuildConfig.MD5_HASH}")
    suspend fun getCharacterDetails(@Path("characterId") characterId: Int): Response<MarvelResponse<Character>>

    @GET("comics?ts=1&apikey=${BuildConfig.PUBLIC_API}&hash=${BuildConfig.MD5_HASH}")
    suspend fun getAllComics(): Response<MarvelResponse<ComicBook>>

    @GET("comics/{comicId}")
    suspend fun getComicDetails(@Path("comicId") comicId: Int): Response<MarvelResponse<ComicBook>>

    @GET("events?ts=1&apikey=${BuildConfig.PUBLIC_API}&hash=${BuildConfig.MD5_HASH}")
    suspend fun getAllEvents(): Response<MarvelResponse<Event>>

    @GET("events/{eventId}?ts=1&apikey=${BuildConfig.PUBLIC_API}&hash=${BuildConfig.MD5_HASH}")
    suspend fun getEventDetails(@Path("eventId") eventId: String): Response<MarvelResponse<Event>>

    @GET("events/{eventId}/characters?ts=1&apikey=${BuildConfig.PUBLIC_API}&hash=${BuildConfig.MD5_HASH}")
    suspend fun getCharactersAtEvent(@Path("eventId") eventId: Int): Response<MarvelResponse<Character>>

    @GET("events/{eventId}/creators?ts=1&apikey=${BuildConfig.PUBLIC_API}&hash=${BuildConfig.MD5_HASH}")
    suspend fun getEventCreators(@Path("eventId") eventId: Int): Response<MarvelResponse<Creator>>

    @GET("creators/{creatorId}/events?ts=1&apikey=${BuildConfig.PUBLIC_API}&hash=${BuildConfig.MD5_HASH}")
    suspend fun getCreatorRole(@Path("creatorId") creatorId: Int): Response<MarvelResponse<Event>>

    companion object {
        private var retrofitService: MarvelService? = null

        fun getInstance() : MarvelService {
            if (retrofitService == null ) {
                val interceptor = HttpLoggingInterceptor()
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

                val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()

                val retrofit = Retrofit.Builder()
                    .client(client)
                    .baseUrl("https://gateway.marvel.com/v1/public/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                retrofitService = retrofit.create(MarvelService::class.java)
            }
            return requireNotNull(retrofitService)
        }
    }
}