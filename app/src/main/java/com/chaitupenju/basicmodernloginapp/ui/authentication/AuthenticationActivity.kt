package com.chaitupenju.basicmodernloginapp.ui.authentication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.chaitupenju.basicmodernloginapp.R
import com.chaitupenju.basicmodernloginapp.databinding.ActivityAuthenticationBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthenticationActivity : AppCompatActivity() {

    private lateinit var authActivity: ActivityAuthenticationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        authActivity = DataBindingUtil.setContentView(this@AuthenticationActivity, R.layout.activity_authentication)
    }
}