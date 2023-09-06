package com.example.newsapp.data.api

import com.example.newsapp.data.model.dto.NewsResponse
import com.example.newsapp.utils.Constance
import retrofit2.http.GET
import retrofit2.http.Query
import java.nio.channels.spi.AbstractSelectionKey

interface NewsApi {
    @GET(Constance.END_POINT)
    suspend fun getNews(
        @Query("page") page: Int,
        @Query("sources") sources: String,
        @Query("apiKey") apiKey: String = Constance.API_KEY
    ): NewsResponse

    @GET(Constance.END_POINT)
    suspend fun searchNews(
        @Query("q") searchQuery: String,
        @Query("page") page: Int,
        @Query("sources") sources: String,
        @Query("apiKey") apiKey: String = Constance.API_KEY
    ): NewsResponse
}