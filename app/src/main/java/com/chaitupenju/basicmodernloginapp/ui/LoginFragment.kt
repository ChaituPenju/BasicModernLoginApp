package com.chaitupenju.basicmodernloginapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.chaitupenju.basicmodernloginapp.databinding.FragmentLoginBinding
import com.chaitupenju.basicmodernloginapp.repository.AuthRepository


class LoginFragment : Fragment() {

    private lateinit var loginBinding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        loginBinding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        return loginBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val service = com.chaitupenju.basicmodernloginapp.network.AuthApi.create()
        val authRepository = AuthRepository(service)

        loginBinding.btnLogin.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launchWhenStarted {
                println(authRepository.login(username = loginBinding.etUsername.text.toString(), password = loginBinding.etPassword.text.toString()))
            }
        }
    }

}