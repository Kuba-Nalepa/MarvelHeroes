package com.example.marvelheroes.data.model

import com.google.gson.annotations.SerializedName

data class ComicBookDetails(
    @SerializedName("title")
    val title: String,
    @SerializedName("thumbnail")
    val thumbnail: ComicBookImage
)

data class ComicBookImage(
    @SerializedName("path")
    val path: String,
    @SerializedName("extension")
    val extension: String
)
