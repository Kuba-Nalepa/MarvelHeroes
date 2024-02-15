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
    @SerializedName("limit")
    val limit: Int,
    @SerializedName("results")
    val results: List<T>
)


data class Character (
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("thumbnail")
    val thumbnail: Thumbnail? = null,
    @SerializedName("resourceURI")
    val resourceURI: String? = null,
)


data class ComicBook (
    @SerializedName("id")
    val id: Int,
    @SerializedName("digitalId")
    val digitalId: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("variantDescription")
    val variantDescription: String,
    @SerializedName("textObjects")
    val description: List<TextObject>,
    @SerializedName("resourceURI")
    val resourceURI: String,
    @SerializedName("urls")
    val urls: List<URL>,
    @SerializedName("prices")
    val prices: List<Price>,
    @SerializedName("thumbnail")
    val thumbnail: Thumbnail,
    @SerializedName("images")
    val images: List<Thumbnail>,
    @SerializedName("characters")
    val featuringCharacters: FeaturingCharacters,
    @SerializedName("pageCount")
    val pageCount: Int,
    @SerializedName("format")
    val coverType: String

)

data class Event(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("start")
    val startDate: String? = null,
    @SerializedName("end")
    val endDate: String? = null,
    @SerializedName("thumbnail")
    val thumbnail: Thumbnail,
    @SerializedName("characters")
    val featuringCharacters: FeaturingCharacters,
    @SerializedName("creators")
    val featuringCreators: FeaturingCreators
    )

data class FeaturingCharacters (
    @SerializedName("items")
    var items: List<Character>,

)

data class FeaturingCreators(
    @SerializedName("items")
    var items: List<Creator>,
)

data class Creator(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("fullName")
    val fullName: String?,
    @SerializedName("role")
    val role: String?
)

data class Comics (
    @SerializedName("available")
    val available: Long,
    @SerializedName("collectionURI")
    val collectionURI: String,
    @SerializedName("items")
    val items: List<StoriesItem>,
    @SerializedName("returned")
    val returned: Long
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
    val price: Float
)

data class TextObject (
    @SerializedName("text")
    val text: String
)


data class Thumbnail (
    @SerializedName("path")
    val path: String,
    @SerializedName("extension")
    val extension: Extension
)


enum class Extension(val value: String) {
    @SerializedName("gif") gif("gif"),
    @SerializedName("jpg") jpg("jpg"),
    @SerializedName("png") png("png");
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
