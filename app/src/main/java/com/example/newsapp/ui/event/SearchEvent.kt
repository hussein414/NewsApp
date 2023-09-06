package com.example.newsapp.ui.event

sealed class SearchEvent {
    data class UpdateSearchQuery(val searchQuery: String) : SearchEvent()
    object SearchNews:SearchEvent()
}
