package com.chaitupenju.basicmodernloginapp.ui.authentication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.util.PatternsCompat
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.chaitupenju.basicmodernloginapp.utils.Response
import com.chaitupenju.basicmodernloginapp.data.UserPreferences
import com.chaitupenju.basicmodernloginapp.databinding.FragmentLoginBinding
import com.chaitupenju.basicmodernloginapp.network.api.AuthApi
import com.chaitupenju.basicmodernloginapp.network.api.AuthApiImpl
import com.chaitupenju.basicmodernloginapp.repository.AuthRepository
import com.chaitupenju.basicmodernloginapp.ui.BaseFragment
import com.chaitupenju.basicmodernloginapp.ui.home.HomeActivity
import com.chaitupenju.basicmodernloginapp.utils.createSnackBar
import com.chaitupenju.basicmodernloginapp.utils.startAnActivity
import com.chaitupenju.basicmodernloginapp.utils.visibility
import com.chaitupenju.basicmodernloginapp.viewmodel.AuthViewModel
import com.chaitupenju.basicmodernloginapp.viewmodel.ViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import java.util.regex.Matcher


class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    val service: AuthApi = AuthApiImpl()
    lateinit var userPrefs: UserPreferences

    val authRepository get() = AuthRepository(service, userPrefs)

    val authViewModel by viewModels<AuthViewModel> { ViewModelFactory(authRepository) }

    private var emailValid = false
    private var passwordValid = false

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentLoginBinding {
        userPrefs = UserPreferences(requireContext())
        return FragmentLoginBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registerCredentialTextListeners()

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            authViewModel.loginResult.collectLatest {

                when (it) {
                    is Response.Loading -> binding.pbLogin.visibility(true)

                    is Response.Success -> {
                        binding.pbLogin.visibility(false)
                        authViewModel.saveAccessToken(token = it.data.user?.accessToken ?: "")
                        requireActivity().startAnActivity(HomeActivity::class.java)
                    }

                    is Response.Error -> {
                        binding.pbLogin.visibility(false)
                        createSnackBar(it.errorMessage)
                    }
                }
            }
        }

        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if (emailValid and passwordValid)
                authViewModel.login(username, password)
            else createSnackBar("Please enter valid Credentials to Proceed!")
        }
    }

    private fun registerCredentialTextListeners() {
        binding.etUsername.doOnTextChanged { text, _, _, _ ->
            emailValid = PatternsCompat.EMAIL_ADDRESS.matcher(text!!).matches()
            println("Email valid $emailValid")
            binding.tilUsername.error = if (!emailValid) "Invalid Username/Email" else ""
        }

        binding.etPassword.doOnTextChanged { text, _, _, _ ->
            passwordValid = text?.length!! > 6
            println("Password valid $passwordValid")
            binding.tilPassword.error = if (!passwordValid) "Password must be greater than 6 characters" else ""
        }
    }
}