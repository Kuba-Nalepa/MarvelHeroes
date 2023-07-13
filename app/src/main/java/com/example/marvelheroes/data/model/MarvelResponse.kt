package com.example.marvelheroes.data.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.*
import kotlinx.serialization.json.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*


data class MarvelResponse<T>(
    @SerializedName("code")
    val code: Long,
    @SerializedName("status")
    val status: String,
    @SerializedName("data")
    val marvelData: MarvelData<T>
)


data class MarvelData<T>(
    @SerializedName("offset")
    val offset: Long,
    @SerializedName("total")
    val total: Long,
    @SerializedName("results")
    val results: List<T>
)


data class Character (
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("thumbnail")
    val thumbnail: Thumbnail,
    @SerializedName("resourceURI")
    val resourceURI: String,
)


data class Comics (
    @SerializedName("id")
    val id: String,
    @SerializedName("digitalId")
    val digitalId: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("variantDescription")
    val variantDescription: String,
    @SerializedName("description")
    val description: String,



    @SerializedName("resourceURI")
    val resourceURI: String,
    @SerializedName("urls")
    val urls: List<URL>,
    @SerializedName("series")
    val series: Series,

    @SerializedName("prices")
    val prices: List<Price>,
    @SerializedName("thumbnail")
    val thumbnail: Thumbnail,
    @SerializedName("images")
    val images: List<Thumbnail>,
    @SerializedName("characters")
    val characters: Character,

)

data class ComicsItem (
    @SerializedName("resourceURI")
    val resourceURI: String,
    @SerializedName("name")
    val name: String
)

data class Series (
    @SerializedName("id")
    val id: Long,
    @SerializedName("title")
    val title: String,
    @SerializedName("variantDescription")
    val variantDescription: String,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("resourceURI")
    val resourceURI: String,
    @SerializedName("prices")
    val prices: List<Price>,
    @SerializedName("thumbnail")
    val thumbnail: Thumbnail,
    @SerializedName("images")
    val images: List<Thumbnail>,
)

data class SeriesItem (
    val resourceURI: String,
    val name: String
)


data class Stories (
    @SerializedName("available")
    val available: Long,
    @SerializedName("collectionURI")
    val collectionURI: String,
    @SerializedName("items")
    val items: List<StoriesItem>,
    @SerializedName("returned")
    val returned: Long
)


data class StoriesItem (
    @SerializedName("resourceURI")
    val resourceURI: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("type")
    val type: ItemType
)


enum class ItemType(val value: String) {
    @SerialName("cover") Cover("cover"),
    @SerialName("") Empty(""),
    @SerialName("interiorStory") InteriorStory("interiorStory");
}


data class Price (
    val type: PriceType,
    val price: Double
)

enum class PriceType {
    DigitalPurchasePrice,
    PrintPrice
}


data class Thumbnail (
    @SerializedName("path")
    val path: String,
    @SerializedName("extension")
    val extension: Extension
)


enum class Extension(val value: String) {
    @SerializedName("gif") gif("gif"),
    @SerializedName("jpg") jpg("jpg");
}


data class URL (
    @SerializedName("type")
    val type: URLType,
    @SerializedName("url")
    val url: String
)


enum class URLType(val value: String) {
    @SerializedName("comiclink") Comiclink("comiclink"),
    @SerializedName("detail") Detail("detail"),
    @SerializedName("wiki") Wiki("wiki");
}
