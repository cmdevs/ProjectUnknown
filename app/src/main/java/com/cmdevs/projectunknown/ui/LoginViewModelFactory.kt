package com.cmdevs.projectunknown.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class LoginViewModelFactory(
    val loginViewModelDelegate: LoginViewModelDelegate
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        //return kodein.direct.Instance(TT(modelClass))
        return LoginViewModel(loginViewModelDelegate) as T
    }
}
