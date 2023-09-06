package com.example.newsapp.data.usecase.db

import com.example.newsapp.data.local.db.NewsDao
import com.example.newsapp.data.model.dto.Article
import com.example.newsapp.data.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSavedArticles (private val newsDao: NewsDao){
    operator fun invoke(): Flow<List<Article>> {
        return newsDao.getArticles()
    }
}