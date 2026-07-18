package com.example.mangaexplorer.data.remote

import com.example.mangaexplorer.data.model.GenreResponse
import com.example.mangaexplorer.data.model.KitsuDetailsResponse
import com.example.mangaexplorer.data.model.KitsuResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
interface MangaApi {
    @GET("manga")
    suspend fun getTopRatedManga(
        @Query("sort") sort: String = "-averageRating",
        @Query("page[limit]") limit: Int = 20
    ): KitsuResponse


    @GET("manga")
    suspend fun getPopularManga(
        @Query("sort") sort: String = "-userCount",
        @Query("page[limit]") limit: Int = 20
    ): KitsuResponse


    @GET("manga")
    suspend fun getTrendingManga(
        @Query("sort") sort: String = "-favoritesCount",
        @Query("page[limit]") limit: Int = 20
    ): KitsuResponse


    @GET("manga/{id}/genres")
    suspend fun getGenres(
        @Path("id") id: String
    ): GenreResponse


     @GET("manga")
    suspend fun searchManga(
        @Query("filter[text]") query: String
    ): KitsuResponse


     @GET("manga/{id}")
    suspend fun getMangaDetails(
        @Path("id") id: String
    ): KitsuDetailsResponse

}