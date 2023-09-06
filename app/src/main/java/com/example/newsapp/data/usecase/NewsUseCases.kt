package com.example.newsapp.data.usecase

import com.example.newsapp.data.usecase.db.DeleteArticle
import com.example.newsapp.data.usecase.db.GetSavedArticle
import com.example.newsapp.data.usecase.db.GetSavedArticles
import com.example.newsapp.data.usecase.db.UpsertArticle
import com.example.newsapp.data.usecase.dto.GetNews
import com.example.newsapp.data.usecase.dto.SearchNews

data class NewsUseCases(
    val getNews: GetNews,
    val searchNews: SearchNews,
    val upsertArticle: UpsertArticle,
    val deleteArticle: DeleteArticle,
    val getSavedArticle: GetSavedArticle,
    val getSavedArticles: GetSavedArticles
)
