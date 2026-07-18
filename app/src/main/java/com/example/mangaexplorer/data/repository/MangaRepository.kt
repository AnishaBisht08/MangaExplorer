package com.example.mangaexplorer.data.repository

import com.example.mangaexplorer.data.remote.MangaApi

class MangaRepository(
    private val api: MangaApi
) {

    suspend fun getTopRatedManga() = api.getTopRatedManga()
    suspend fun getPopularManga() = api.getPopularManga()
    suspend fun getTrendingManga() = api.getTrendingManga()
    suspend fun getGenres(id: String) = api.getGenres(id)

    suspend fun searchManga(query: String) = api.searchManga(query)

    suspend fun getMangaDetails(id: String) = api.getMangaDetails(id)
}