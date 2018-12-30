package com.cmdevs.projectunknown.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth

class LoginViewModelProviderFactory(
    val signInViewModelDelegate: SignInViewModelDelegate
) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>) = when {
        modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
            LoginViewModel(
                signInViewModelDelegate
            )
        }
        else -> super.create(modelClass)
    } as T
}