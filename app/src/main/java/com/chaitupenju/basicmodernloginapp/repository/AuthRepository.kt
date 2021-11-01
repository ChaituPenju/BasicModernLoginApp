package com.chaitupenju.basicmodernloginapp.repository

import com.chaitupenju.basicmodernloginapp.network.api.AuthApi
import com.chaitupenju.basicmodernloginapp.data.UserPreferences

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