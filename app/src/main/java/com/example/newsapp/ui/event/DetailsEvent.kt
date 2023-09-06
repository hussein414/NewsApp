package com.example.newsapp.ui.event

import com.example.newsapp.data.model.dto.Article

sealed class DetailsEvent {
    object SavedArticle : DetailsEvent()
    data class UpsertDeleteArticle(val article: Article) : DetailsEvent()
    object RemoveSideEffect : DetailsEvent()
}
