package com.chaitupenju.basicmodernloginapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.chaitupenju.basicmodernloginapp.data.Response
import com.chaitupenju.basicmodernloginapp.databinding.FragmentLoginBinding
import com.chaitupenju.basicmodernloginapp.repository.AuthRepository
import com.chaitupenju.basicmodernloginapp.viewmodel.AuthViewModel
import com.chaitupenju.basicmodernloginapp.viewmodel.ViewModelFactory
import kotlinx.coroutines.flow.collectLatest


class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    val service = com.chaitupenju.basicmodernloginapp.network.AuthApi.create()
    val authRepository = AuthRepository(service)

    val authViewModel by viewModels<AuthViewModel> { ViewModelFactory(authRepository) }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentLoginBinding {
        return FragmentLoginBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            authViewModel.login(username, password)

            viewLifecycleOwner.lifecycleScope.launchWhenStarted {
                authViewModel.loginResult.collectLatest {
                    when (it) {
                        is Response.Success -> println(it.data)
                        is Response.Error -> println(it.errorMessage)
                        is Response.Loading -> println("Loading...")
                    }
                }
            }
        }
    }

}