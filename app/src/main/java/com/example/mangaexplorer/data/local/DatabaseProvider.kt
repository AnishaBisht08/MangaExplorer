package com.example.mangaexplorer.data.local

import android.content.Context
import androidx.room.Room
import kotlin.concurrent.Volatile

object DatabaseProvider {

    @Volatile
    private var INSTANCE : FavoriteDatabase? = null

    fun getDatabase(context: Context): FavoriteDatabase{
        return INSTANCE ?: synchronized(this){
            val instance = Room.databaseBuilder(
                context.applicationContext,
                FavoriteDatabase::class.java,
                "favorite_database"
            ).build()

            INSTANCE = instance
            instance
        }
    }
}