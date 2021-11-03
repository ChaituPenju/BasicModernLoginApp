package com.chaitupenju.basicmodernloginapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.chaitupenju.basicmodernloginapp.data.UserPreferences
import com.chaitupenju.basicmodernloginapp.databinding.FragmentHomeBinding
import com.chaitupenju.basicmodernloginapp.ui.BaseFragment
import com.chaitupenju.basicmodernloginapp.ui.authentication.AuthenticationActivity
import com.chaitupenju.basicmodernloginapp.utils.Response
import com.chaitupenju.basicmodernloginapp.utils.startAnActivity
import com.chaitupenju.basicmodernloginapp.utils.visibility
import com.chaitupenju.basicmodernloginapp.viewmodel.HomeViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val homeViewModel by activityViewModels<HomeViewModel>()
    private val userPrefs: UserPreferences get() = (activity as HomeActivity).userPrefs

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding {

        return FragmentHomeBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            val accessToken = userPrefs.accessToken.first()
            homeViewModel.getUser( accessToken ?: "")

            homeViewModel.userState.collectLatest {
                when (it) {
                    is Response.Loading -> {
                        binding.pbHome.visibility(true)
                        updateHomeUI("Loading Details....")
                    }

                    is Response.Success -> {
                        binding.pbHome.visibility(false)
                        updateHomeUI(it.data.convertToString())
                    }

                    is Response.Error -> {
                        binding.pbHome.visibility(false)
                        updateHomeUI(it.errorMessage)
                    }
                }
            }
        }

        binding.btnLogout.setOnClickListener {
            binding.pbHome.visibility(true)
            viewLifecycleOwner.lifecycleScope.launchWhenStarted {
                Toast.makeText(requireContext(), homeViewModel.logout(), Toast.LENGTH_LONG).show()
                userPrefs.saveAccessToken("")
                binding.pbHome.visibility(false)
                requireActivity().startAnActivity(AuthenticationActivity::class.java)
            }
        }
    }

    private fun updateHomeUI(data: String) {
        binding.tvUserDetails.text = data
    }

}