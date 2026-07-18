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


class MangaViewModel : ViewModel() {

    private val repository = MangaRepository(RetrofitInstance.api)


    private val _topRatedList = MutableStateFlow<List<Manga>>(emptyList())
    val topRatedList = _topRatedList.asStateFlow()


    private val _popularList = MutableStateFlow<List<Manga>>(emptyList())
    val popularList = _popularList.asStateFlow()


    private val _trendingList = MutableStateFlow<List<Manga>>(emptyList())
    val trendingList = _trendingList.asStateFlow()

    private val _searchResults = MutableStateFlow<List<Manga>>(emptyList())
    val searchResults = _searchResults.asStateFlow()


    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error


    init {
        viewModelScope.launch {
            loadTopRatedManga()
            loadPopularManga()
            loadTrendingManga()

        }
    }


    private suspend fun loadTopRatedManga() {

        try {

            _topRatedList.value =
                repository.getTopRatedManga().data

        } catch (e: Exception) {

            _error.value = e.message

        }


    }


    private suspend fun loadPopularManga() {


        try {

            _popularList.value =
                repository.getPopularManga().data

        } catch (e: Exception) {
            _error.value = e.message


        }


    }


    private suspend fun loadTrendingManga() {


        try {
            _trendingList.value =
                repository.getTrendingManga().data

        } catch (e: Exception) {

            _error.value = e.message

        }

    }



    fun searchManga(query: String) {

        if (query.isBlank()) {

            _searchResults.value = emptyList()
            return

        }


        viewModelScope.launch {

            try {

                val result = repository.searchManga(query).data

                _searchResults.value = result


            } catch (e: Exception) {

                e.printStackTrace()

                _searchResults.value = emptyList()

            }

        }

    }

}