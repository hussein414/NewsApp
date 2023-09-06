package com.example.newsapp.data.model.dto

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)