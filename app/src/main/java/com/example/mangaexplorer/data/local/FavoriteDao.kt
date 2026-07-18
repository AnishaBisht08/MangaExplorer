package com.example.mangaexplorer.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mangaexplorer.data.model.FavoriteManga
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFavorite(
        favoriteManga: FavoriteManga
    )

    @Query("SELECT * FROM favorite_manga")
    fun getAllFavorite(): Flow<List<FavoriteManga>>

    @Delete
    suspend fun deleteFavorite(
        favoriteManga: FavoriteManga
    )

    @Query("SELECT EXISTS(SELECT 1 FROM favorite_manga WHERE id = :id)")
    suspend fun isFavorite(
        id: String
    ): Boolean
}