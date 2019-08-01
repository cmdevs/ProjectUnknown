package com.cmdevs.projectunknown.ui

import android.os.Bundle
import com.cmdevs.projectunknown.R
import org.kodein.di.generic.instance

class LoginActivity : BaseActivity() {

    val loginViewModelFactory by instance<LoginViewModelFactory>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }
}