package com.chaitupenju.basicmodernloginapp.ui.authentication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.chaitupenju.basicmodernloginapp.data.Response
import com.chaitupenju.basicmodernloginapp.data.UserPreferences
import com.chaitupenju.basicmodernloginapp.databinding.FragmentLoginBinding
import com.chaitupenju.basicmodernloginapp.repository.AuthRepository
import com.chaitupenju.basicmodernloginapp.ui.BaseFragment
import com.chaitupenju.basicmodernloginapp.ui.home.HomeActivity
import com.chaitupenju.basicmodernloginapp.utils.createToast
import com.chaitupenju.basicmodernloginapp.utils.startAnActivity
import com.chaitupenju.basicmodernloginapp.utils.visibility
import com.chaitupenju.basicmodernloginapp.viewmodel.AuthViewModel
import com.chaitupenju.basicmodernloginapp.viewmodel.ViewModelFactory
import kotlinx.coroutines.flow.collectLatest


class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    val service = com.chaitupenju.basicmodernloginapp.network.AuthApi.create()
    val authRepository = AuthRepository(service)

    val authViewModel by viewModels<AuthViewModel> { ViewModelFactory(authRepository) }
    val userPrefs: UserPreferences get() = UserPreferences(requireContext())

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentLoginBinding {
        return FragmentLoginBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            authViewModel.loginResult.collectLatest {
                binding.pbLogin.visibility(false)

                when (it) {
                    is Response.Loading -> binding.pbLogin.visibility(true)
                    is Response.Success -> {
                        binding.pbLogin.visibility(false)
                        userPrefs.saveAccessToken(token = it.data.accessToken)
                        requireActivity().startAnActivity(HomeActivity::class.java)
                    }
                    is Response.Error -> {
                        binding.pbLogin.visibility(false)
                        createToast(it.errorMessage)
                    }
                }
            }
        }

        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            authViewModel.login(username, password)
        }
    }

}