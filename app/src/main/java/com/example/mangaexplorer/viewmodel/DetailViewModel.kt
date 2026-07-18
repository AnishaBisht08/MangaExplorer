package com.example.mangaexplorer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mangaexplorer.data.model.Manga
import com.example.mangaexplorer.data.remote.RetrofitInstance
import com.example.mangaexplorer.data.repository.MangaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailViewModel: ViewModel() {
    private val repository = MangaRepository(RetrofitInstance.api)

    private val _selectedManga = MutableStateFlow<Manga?>(null)
    val selectedManga = _selectedManga.asStateFlow()


    private val _genres = MutableStateFlow<List<String>>(emptyList())
    val genres = _genres.asStateFlow()


    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error


    fun getMangaDetails(id: String) {
        viewModelScope.launch {
            try {
               _selectedManga.value = repository.getMangaDetails(id).data

            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }

     fun loadGenres(id: String) {
        viewModelScope.launch {
            try {
               _genres.value = repository.getGenres(id).data.map { it.attributes.name }

            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }



}