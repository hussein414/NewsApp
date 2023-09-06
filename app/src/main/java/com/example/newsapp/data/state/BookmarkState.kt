package com.example.newsapp.data.state

import com.example.newsapp.data.model.dto.Article

data class BookmarkState(
    val articles: List<Article> = emptyList()
)
