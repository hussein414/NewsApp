package com.example.newsapp.data.model

import android.icu.text.CaseMap.Title
import android.media.Image
import androidx.annotation.DrawableRes
import java.io.FileDescriptor

data class Page(
    val title: String,
    val description: String,
    @DrawableRes val image: Int
)
