package com.example.mangaexplorer.data.model

import com.google.gson.annotations.SerializedName

data class GenreResponse(
    @SerializedName("data")
    val data: List<Genre>
)

data class Genre(
    @SerializedName("attributes")
    val attributes: GenreAttributes
)


data class GenreAttributes(
    @SerializedName("names")
    val name: String
)