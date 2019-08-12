package com.cmdevs.projectunknown.ui

import android.os.Bundle
import androidx.lifecycle.Observer
import com.cmdevs.projectunknown.R
import com.cmdevs.projectunknown.util.viewModelProivder
import org.kodein.di.generic.instance

class LoginActivity : BaseActivity() {

    val loginViewModelFactory by instance<LoginViewModelFactory>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val loginViewModel = viewModelProivder<LoginViewModel>(loginViewModelFactory)
        loginViewModel.observeUserState.observe(this, Observer {

        })
    }
}