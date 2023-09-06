package com.example.newsapp.data.usecase.dto

import androidx.paging.PagingData
import com.example.newsapp.data.model.dto.Article
import com.example.newsapp.data.repository.NewsRepository
import com.example.newsapp.data.source.NewsPagingSource
import kotlinx.coroutines.flow.Flow

class GetNews(private val repository: NewsRepository) {
    operator fun invoke(source:List<String>):Flow<PagingData<Article>> =
        repository.getNews(source = source)
}