package com.ukejee.prototechnicaltask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ukejee.prototechnicaltask.ui.login.LoginFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        savedInstanceState ?: addFragment()
    }

    private fun addFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, LoginFragment())
        transaction.commit()
    }
}