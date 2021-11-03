package com.chaitupenju.basicmodernloginapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chaitupenju.basicmodernloginapp.data.User
import com.chaitupenju.basicmodernloginapp.repository.UserRepository
import com.chaitupenju.basicmodernloginapp.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: UserRepository
): ViewModel() {

    private val _userState = MutableStateFlow<Response<User>>(Response.Loading)
    val userState: StateFlow<Response<User>> = _userState

    fun getUser(accessToken: String) = viewModelScope.launch {
        _userState.update { repository.getUser(accessToken) }
    }

    suspend fun logout(): String {
        var logoutMessage = ""

        when (val it = repository.logout()) {
            is Response.Success -> logoutMessage = it.data.message
            is Response.Error -> logoutMessage = it.errorMessage
            else -> Unit
        }

        return logoutMessage
    }

}