package com.cmdevs.projectunknown.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cmdevs.projectunknown.ui.SignInViewModelDelegate

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