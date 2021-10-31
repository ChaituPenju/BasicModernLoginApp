package com.chaitupenju.basicmodernloginapp.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class UserPreferences(
    private val context: Context
) {

    companion object {
        private const val DATA_STORE_NAME = "auth_data_store"
        private val ACCESS_TOKEN_KEY = stringPreferencesKey(name = "access_token_key")
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DATA_STORE_NAME)
    }

    private val appContext = context.applicationContext

    val accessToken: Flow<String?>
        get() = appContext.dataStore.data.map { authPrefs ->
            authPrefs[ACCESS_TOKEN_KEY]
        }

    suspend fun saveAccessToken(token: String) {
        appContext.dataStore.edit { authPrefs ->
            authPrefs[ACCESS_TOKEN_KEY] = token
        }
    }
}