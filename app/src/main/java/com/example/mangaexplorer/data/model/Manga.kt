package com.example.mangaexplorer.data.model

import com.google.gson.annotations.SerializedName

data class Manga(
    @SerializedName("id")
    val id: String,

    @SerializedName("attributes")
    val attributes: Attributes
)

data class Attributes(
    @SerializedName("canonicalTitle")
    val canonicalTitle: String?,

    @SerializedName("synopsis")
    val synopsis: String?,

    @SerializedName("averageRating")
    val averageRating: String?,

    @SerializedName("posterImage")
    val posterImage: PosterImage?,

    @SerializedName("coverImage")
    val coverImage: CoverImage?,

    @SerializedName("status")
    val status: String?,

    @SerializedName("startDate")
    val startDate: String?,

    @SerializedName("slug")
    val slug: String
)
