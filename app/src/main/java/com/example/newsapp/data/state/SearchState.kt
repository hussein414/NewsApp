package com.example.newsapp.data.state

import androidx.paging.PagingData
import com.example.newsapp.data.model.dto.Article
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Query

data class SearchState(
    val searchQuery: String = "",
    val articles: Flow<PagingData<Article>>? = null
)