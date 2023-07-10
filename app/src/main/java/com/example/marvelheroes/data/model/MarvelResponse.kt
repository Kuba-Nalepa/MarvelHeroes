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
    @SerializedName("copyright")
    val copyright: String,
    @SerializedName("attributionText")
    val attributionText: String,
    @SerializedName("attributionHTML")
    val attributionHTML: String,
    @SerializedName("etag")
    val etag: String,
    @SerializedName("data")
    val marvelData: MarvelData<T>
)


data class MarvelData<T>(
    @SerializedName("offset")
    val offset: Long,
    @SerializedName("limit")
    val limit: Long,
    @SerializedName("total")
    val total: Long,
    @SerializedName("count")
    val count: Long,
    @SerializedName("results")
    val results: List<T>
)


data class CharacterDetails (
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("modified")
    val modified: String,
    @SerializedName("thumbnail")
    val thumbnail: Thumbnail,
    @SerializedName("resourceURI")
    val resourceURI: String,
    @SerializedName("comics")
    val comics: Comics,
    @SerializedName("series")
    val series: Comics,
    @SerializedName("stories")
    val stories: Stories,
    @SerializedName("events")
    val events: Comics,
    @SerializedName("urls")
    val urls: List<URL>
)


data class Comics (
    @SerializedName("available")
    val available: Long,
    @SerializedName("collectionURI")
    val collectionURI: String,
    @SerializedName("items")
    val items: List<ComicsItem>,
    @SerializedName("returned")
    val returned: Long
)


data class ComicsItem (
    @SerializedName("resourceURI")
    val resourceURI: String,
    @SerializedName("name")
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


data class Thumbnail (
    @SerializedName("path")
    val path: String,
    @SerializedName("extension")
    val extension: Extension
)


enum class Extension(val value: String) {
    @SerializedName("gif") GIF("gif"),
    @SerializedName("jpg") Jpg("jpg");
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
