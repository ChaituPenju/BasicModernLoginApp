package com.chaitupenju.basicmodernloginapp.repository

import com.chaitupenju.basicmodernloginapp.data.Response
import com.chaitupenju.basicmodernloginapp.data.UserPreferences
import com.chaitupenju.basicmodernloginapp.network.AuthApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AuthRepository(
    private val api: AuthApi,
    private val preferences: UserPreferences
): BaseRepository() {

    suspend fun login(username: String, password: String) = wrapResponse {
        api.login(username, password)
    }

    suspend fun saveAccessToken(token: String) {
        preferences.saveAccessToken(token)
    }
}