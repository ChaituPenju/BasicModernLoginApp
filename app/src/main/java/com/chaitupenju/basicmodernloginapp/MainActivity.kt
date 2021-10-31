package com.chaitupenju.basicmodernloginapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.chaitupenju.basicmodernloginapp.data.UserPreferences
import com.chaitupenju.basicmodernloginapp.ui.AuthenticationActivity
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userPrefs = UserPreferences(this@MainActivity)

        lifecycleScope.launchWhenCreated {
            userPrefs.accessToken.collectLatest {
                Toast.makeText(this@MainActivity, it ?: "Token is Null!!", Toast.LENGTH_LONG).show()
                startActivity(Intent(this@MainActivity, AuthenticationActivity::class.java))
            }
        }
    }
}