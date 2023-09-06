package com.example.newsapp.data.usecase.db

import com.example.newsapp.data.local.db.NewsDao
import com.example.newsapp.data.model.dto.Article
import com.example.newsapp.data.repository.NewsRepository
import javax.inject.Inject

class UpsertArticle(private val newsDao: NewsDao) {
    suspend operator fun invoke(article: Article) {
        newsDao.upsert(article = article)
    }
}