package com.example.newsapp.data.repository

import androidx.paging.PagingData
import com.example.newsapp.data.model.dto.Article
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Query
import java.security.CodeSource

interface NewsRepository {
    fun getNews(source: List<String>): Flow<PagingData<Article>>
    fun searchNews(searchQuery: String, source: List<String>): Flow<PagingData<Article>>

    suspend fun upsertArticle(article: Article)

    suspend fun deleteArticle(article: Article)

    fun getArticles(): Flow<List<Article>>

    suspend fun getArticle(url: String): Article?
}