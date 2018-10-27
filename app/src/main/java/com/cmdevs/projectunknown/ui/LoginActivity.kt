package com.cmdevs.projectunknown.ui

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.cmdevs.projectunknown.BaseActivity
import com.cmdevs.projectunknown.R
import com.cmdevs.projectunknown.databinding.ActivityLoginBinding
import com.cmdevs.projectunknown.util.viewModelProvder
import com.facebook.CallbackManager
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import org.kodein.di.generic.instance

class LoginActivity : BaseActivity() {

    val factory by instance<LoginViewModelProviderFactory>()
    val firebaseAuth by instance<FirebaseAuth>()
    val googleSignOptions by instance<GoogleSignInOptions>()
    val callbackManager by instance<CallbackManager>()

    lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginViewModel = viewModelProvder(factory)
        bindView()
    }

    fun bindView() {
        DataBindingUtil.setContentView<ActivityLoginBinding>(this, R.layout.activity_login).run {
            viewmodel = loginViewModel
        }
    }

}