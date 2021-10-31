package com.chaitupenju.basicmodernloginapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chaitupenju.basicmodernloginapp.data.Response
import com.chaitupenju.basicmodernloginapp.data.User
import com.chaitupenju.basicmodernloginapp.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: UserRepository
): ViewModel() {

    private val _userState = MutableStateFlow<Response<User>>(Response.Loading)
    val userState: StateFlow<Response<User>> = _userState

    fun getUser(accessToken: String) = viewModelScope.launch {
        _userState.update { repository.getUser(accessToken) }
    }

}