package com.example.newsapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.newsapp.data.usecase.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(newsUseCases: NewsUseCases) : ViewModel() {
    val news = newsUseCases.getNews(
        source = listOf("bbc-news", "abc-news", "al-jazeera-news")
    ).cachedIn(viewModelScope)
}