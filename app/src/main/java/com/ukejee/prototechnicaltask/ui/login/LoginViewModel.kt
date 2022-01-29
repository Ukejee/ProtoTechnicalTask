package com.ukejee.prototechnicaltask.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.text.TextUtils
import android.util.Patterns


class LoginViewModel : ViewModel() {

    private val userAuthenticated: MutableLiveData<Boolean> = MutableLiveData()

    private var errorMessage: String = ""

    fun getErrorMessage() = errorMessage

    fun getUserAuthenticated() = userAuthenticated

    fun authenticateUser(email: String, password: String) {

        if (!isValidEmail(email)) {
            errorMessage = "Please enter a valid email"
            userAuthenticated.value = false
        } else if (password.length < 5) {
            errorMessage = "Password should be at least five characters long"
            userAuthenticated.value = false
        } else {
            userAuthenticated.value = true
        }
    }

    private fun isValidEmail(target: CharSequence?): Boolean {
        return if (TextUtils.isEmpty(target)) {
            false
        } else {
            Patterns.EMAIL_ADDRESS.matcher(target).matches()
        }
    }
}