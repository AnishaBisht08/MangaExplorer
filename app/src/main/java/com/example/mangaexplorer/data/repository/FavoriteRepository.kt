package com.example.mangaexplorer.data.repository

import com.example.mangaexplorer.data.local.FavoriteDao
import com.example.mangaexplorer.data.model.FavoriteManga

class FavoriteRepository(
    private val dao: FavoriteDao
) {

    suspend fun insertFavorite(favoriteManga: FavoriteManga){
        dao.insertFavorite(favoriteManga)
    }

    suspend fun deleteFavorite(favoriteManga: FavoriteManga){
        dao.deleteFavorite(favoriteManga)
    }

    fun getAllFavorite() = dao.getAllFavorite()

    suspend fun isFavorite(id: String): Boolean{
        return dao.isFavorite(id)
    }

}