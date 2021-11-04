package com.chaitupenju.basicmodernloginapp.repository

import com.chaitupenju.basicmodernloginapp.data.UserPreferences
import com.chaitupenju.basicmodernloginapp.network.api.AuthApi
import javax.inject.Inject

class AuthRepository @Inject constructor(
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