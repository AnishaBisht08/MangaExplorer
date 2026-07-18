package com.example.mangaexplorer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mangaexplorer.data.model.FavoriteManga
import com.example.mangaexplorer.data.repository.FavoriteRepository
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val repository: FavoriteRepository
): ViewModel()
{

    val favorites = repository.getAllFavorite()

     fun insertFavorite(favoriteManga: FavoriteManga){
        viewModelScope.launch {
            repository.insertFavorite(favoriteManga)
        }
    }

     fun deleteFavorite(favoriteManga: FavoriteManga) {
        viewModelScope.launch {
            repository.deleteFavorite(favoriteManga)
        }
    }

}