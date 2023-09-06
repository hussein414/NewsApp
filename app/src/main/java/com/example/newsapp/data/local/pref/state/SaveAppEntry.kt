package com.example.newsapp.data.local.pref.state

import com.example.newsapp.data.local.pref.manager.LocalUserManager

class SaveAppEntry(private val localUserManager: LocalUserManager) {
    suspend operator fun invoke() {
        localUserManager.saveAppEntry()
    }
}