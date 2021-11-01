package com.chaitupenju.basicmodernloginapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chaitupenju.basicmodernloginapp.data.LoginResponse
import com.chaitupenju.basicmodernloginapp.utils.Response
import com.chaitupenju.basicmodernloginapp.repository.AuthRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class AuthViewModel(
    private val repository: AuthRepository
): ViewModel() {

    private val _loginResult: MutableSharedFlow<Response<LoginResponse>> = MutableSharedFlow(replay = 1)
    val loginResult: SharedFlow<Response<LoginResponse>>
        get() = _loginResult

    fun login(username: String, password: String) = viewModelScope.launch {
        _loginResult.tryEmit(Response.Loading)
        _loginResult.emit(repository.login(username, password))
    }

    fun saveAccessToken(token: String) = viewModelScope.launch {
        repository.saveAccessToken(token)
    }

}