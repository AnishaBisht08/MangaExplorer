package com.example.mangaexplorer.data.model

import com.google.gson.annotations.SerializedName

data class KitsuResponse(
    @SerializedName("data")
    val data: List<Manga>
)



