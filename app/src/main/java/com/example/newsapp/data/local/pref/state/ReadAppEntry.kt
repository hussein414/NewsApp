package com.example.newsapp.data.local.pref.state

import com.example.newsapp.data.local.pref.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow

class ReadAppEntry(private val localUserManager: LocalUserManager) {
     operator fun invoke(): Flow<Boolean> =
        localUserManager.readAppEntry()

}