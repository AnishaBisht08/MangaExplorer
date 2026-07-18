package com.example.mangaexplorer.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_manga")
data class FavoriteManga(
    @PrimaryKey
    val id: String,
    val title: String?,
    val imageUrl: String?,
    val rating: String?
)