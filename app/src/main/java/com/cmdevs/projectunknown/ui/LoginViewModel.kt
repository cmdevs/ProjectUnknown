package com.cmdevs.projectunknown.ui

import android.util.Log
import androidx.lifecycle.ViewModel

class LoginViewModel(
    loginViewModelDelegate: LoginViewModelDelegate
) : ViewModel(), LoginViewModelDelegate by loginViewModelDelegate {

    fun onSignIn() {
        Log.d("cylee", "onSignIn()")
        emitSignInRequest()
    }

    fun onSignOut() {
        emitSignOutRequest()
    }
}