package com.chaitupenju.basicmodernloginapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.chaitupenju.basicmodernloginapp.data.UserPreferences
import com.chaitupenju.basicmodernloginapp.ui.authentication.AuthenticationActivity
import com.chaitupenju.basicmodernloginapp.ui.home.HomeActivity
import com.chaitupenju.basicmodernloginapp.utils.startAnActivity
import kotlinx.coroutines.flow.collectLatest

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userPrefs = UserPreferences(this@MainActivity)

        lifecycleScope.launchWhenCreated {
            userPrefs.accessToken.collectLatest {
                val activity = if (it == null) AuthenticationActivity::class.java else HomeActivity::class.java
                startAnActivity(activity)
            }
        }
    }
}