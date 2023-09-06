package com.example.newsapp.data.local.pref.manager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.newsapp.utils.Constance

object PreferencesKey {
    val APP_ENTRY = booleanPreferencesKey(name = Constance.APP_ENTRY)
    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = Constance.USER_SETTING)
}