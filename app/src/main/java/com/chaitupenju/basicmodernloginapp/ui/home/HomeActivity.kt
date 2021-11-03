package com.chaitupenju.basicmodernloginapp.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.chaitupenju.basicmodernloginapp.R
import com.chaitupenju.basicmodernloginapp.data.UserPreferences
import com.chaitupenju.basicmodernloginapp.databinding.ActivityHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var homeBinding: ActivityHomeBinding
    @Inject lateinit var userPrefs: UserPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        homeBinding = DataBindingUtil.setContentView(this@HomeActivity, R.layout.activity_home)
    }
}