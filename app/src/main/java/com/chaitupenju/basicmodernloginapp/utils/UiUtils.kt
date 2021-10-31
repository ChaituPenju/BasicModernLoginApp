package com.chaitupenju.basicmodernloginapp.utils

import android.app.Activity
import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment

fun <A: Activity> Activity.startAnActivity(activity: Class<A>) {
    Intent(this@startAnActivity, activity).also {
        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(it)
    }
}

fun View.visibility(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.GONE
}

fun Fragment.createToast(message: String) {
    Toast.makeText(this@createToast.requireContext(), message, Toast.LENGTH_LONG).show()
}