package com.cmdevs.projectunknown.ui

import androidx.lifecycle.ViewModel

class LoginViewModel(
    val provider: String
) : ViewModel() {

    fun doCheck() {
        println("loginViewModel do check : ${provider}")
    }
}