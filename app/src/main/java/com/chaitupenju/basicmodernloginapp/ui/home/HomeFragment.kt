package com.chaitupenju.basicmodernloginapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.chaitupenju.basicmodernloginapp.data.Response
import com.chaitupenju.basicmodernloginapp.data.User
import com.chaitupenju.basicmodernloginapp.data.UserPreferences
import com.chaitupenju.basicmodernloginapp.databinding.FragmentHomeBinding
import com.chaitupenju.basicmodernloginapp.network.UserApi
import com.chaitupenju.basicmodernloginapp.network.UserApiImpl
import com.chaitupenju.basicmodernloginapp.repository.UserRepository
import com.chaitupenju.basicmodernloginapp.ui.BaseFragment
import com.chaitupenju.basicmodernloginapp.ui.authentication.AuthenticationActivity
import com.chaitupenju.basicmodernloginapp.utils.startAnActivity
import com.chaitupenju.basicmodernloginapp.viewmodel.HomeViewModel
import com.chaitupenju.basicmodernloginapp.viewmodel.ViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    val service: UserApi = UserApiImpl()
    val userPrefs: UserPreferences get() = UserPreferences(requireContext())

    val userRepository = UserRepository(service)

    val homeViewModel by viewModels<HomeViewModel> { ViewModelFactory(userRepository) }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding {

        return FragmentHomeBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val accessToken = runBlocking { userPrefs.accessToken.first() }
        homeViewModel.getUser(accessToken ?: "")

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            homeViewModel.userState.collectLatest {
                when (it) {
                    is Response.Loading -> {
                        updateHomeUI("Loading Details....")
                    }

                    is Response.Success -> {
                        updateHomeUI(it.data.convertToString())
                    }

                    is Response.Error -> {
                        updateHomeUI(it.errorMessage)
                    }
                }
            }
        }

        binding.btnLogout.setOnClickListener {
            requireActivity().startAnActivity(AuthenticationActivity::class.java)
            runBlocking { userPrefs.saveAccessToken("") }
        }
    }

    private fun updateHomeUI(data: String) {
        binding.tvUserDetails.text = data
    }

}