package com.example.mangaexplorer.data.local

import com.example.mangaexplorer.data.model.FavoriteManga
import com.example.mangaexplorer.data.model.Manga

fun Manga.toFavoriteManga(): FavoriteManga{
    return FavoriteManga(
        id = id,
        title = attributes.canonicalTitle ?: "Unknown Title",
        imageUrl = attributes.posterImage?.original ?: "",
        rating = attributes.averageRating ?: "N/A"
    )
}