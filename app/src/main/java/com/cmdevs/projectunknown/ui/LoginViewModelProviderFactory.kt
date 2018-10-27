package com.cmdevs.projectunknown.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class LoginViewModelProviderFactory(
    val provider: String
) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>) = when {
        modelClass.isAssignableFrom(LoginViewModel::class.java) -> LoginViewModel(provider)
        else -> super.create(modelClass)
    } as T
}