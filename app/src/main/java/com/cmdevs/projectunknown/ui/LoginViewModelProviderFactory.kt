package com.cmdevs.projectunknown.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cmdevs.projectunknown.ui.signin.SignInDelegate

class LoginViewModelProviderFactory(
    val signInDelegate: SignInDelegate
) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>) = when {
        modelClass.isAssignableFrom(LoginViewModel::class.java) -> LoginViewModel(signInDelegate)
        else -> super.create(modelClass)
    } as T
}