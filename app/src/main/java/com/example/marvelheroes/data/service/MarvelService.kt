package com.example.marvelheroes.data.service

import com.example.marvelheroes.data.model.MarvelResponse
import com.example.marvelheroes.data.model.CharacterDetails
import com.example.marvelheroes.data.model.ComicsItem
import com.example.marvelheroes.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface MarvelService {

    @GET("characters?ts=1&apikey=${BuildConfig.PUBLIC_API}&hash=${BuildConfig.MD5_HASH}")
    suspend fun getAllCharacters(): Response<MarvelResponse<CharacterDetails>>

    @GET("characters/{characterId}?ts=1&apikey=${BuildConfig.PUBLIC_API}&hash=${BuildConfig.MD5_HASH}")
    suspend fun getCharacterDetails(@Path("characterId") characterId: Int): Response<CharacterDetails>

    @GET("comics")
    suspend fun getAllComics(): Response<List<ComicsItem>>

    @GET("comics/{comicId}")
    suspend fun getComicDetails(@Path("comicId") comicId: Int): Response<ComicsItem>

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