package com.example.mangaexplorer.data.model

import com.google.gson.annotations.SerializedName

data class KitsuDetailsResponse(
    @SerializedName("data")
    val data: Manga
)
