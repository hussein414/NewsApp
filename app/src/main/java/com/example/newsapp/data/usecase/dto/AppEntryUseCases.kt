package com.example.newsapp.data.usecase.dto

import com.example.newsapp.data.local.pref.state.ReadAppEntry
import com.example.newsapp.data.local.pref.state.SaveAppEntry

data class AppEntryUseCases(
    val readAppEntry: ReadAppEntry,
    val saveAppEntry: SaveAppEntry
)
