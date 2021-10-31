package com.chaitupenju.basicmodernloginapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chaitupenju.basicmodernloginapp.data.Response
import com.chaitupenju.basicmodernloginapp.data.User
import com.chaitupenju.basicmodernloginapp.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel(
    private val repository: AuthRepository
): ViewModel() {

    private val _loginResult: MutableStateFlow<Response<User>> = MutableStateFlow(Response.Loading)
    val loginResult: StateFlow<Response<User>>
        get() = _loginResult

    fun login(username: String, password: String) = viewModelScope.launch {
        _loginResult.value = repository.login(username, password)
    }

}