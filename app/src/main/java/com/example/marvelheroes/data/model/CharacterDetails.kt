package com.example.marvelheroes.data.model

import com.example.marvelheroes.domain.model.ComicBook
import com.google.gson.annotations.SerializedName

data class CharacterDetails(
    @SerializedName("name")
    val name: String,
    @SerializedName("image")
    val characterImage: CharacterImage,
    @SerializedName("comics")
    val comicBookList: List<ComicBook>
)

data class CharacterImage(
    @SerializedName("path")
    val path: String,
    @SerializedName("extension")
    val extension: String
)
