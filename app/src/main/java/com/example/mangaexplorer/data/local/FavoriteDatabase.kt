package com.example.mangaexplorer.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mangaexplorer.data.model.FavoriteManga

@Database(
    entities = [FavoriteManga::class],
    version = 2,
    exportSchema = false
)
abstract class FavoriteDatabase: RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
}