package com.example.newsapp.data.usecase.dto

import androidx.paging.PagingData
import com.example.newsapp.data.model.dto.Article
import com.example.newsapp.data.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Query

class SearchNews(private val repository: NewsRepository) {
    operator fun invoke(searchQuery: String, source: List<String>): Flow<PagingData<Article>> =
        repository.searchNews(searchQuery = searchQuery, source = source)
}