package com.cmdevs.projectunknown.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.cmdevs.projectunknown.R
import com.cmdevs.projectunknown.databinding.ActivityLoginBinding
import com.cmdevs.projectunknown.domain.result.EventObserver
import com.cmdevs.projectunknown.util.SignInHandler
import com.cmdevs.projectunknown.util.viewModelProivder
import org.kodein.di.generic.instance

class LoginActivity : BaseActivity() {

    val signInHanlder by instance<SignInHandler>()
    val loginViewModelFactory by instance<LoginViewModelFactory>()

    companion object {
        val RC_SIGN_IN = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_login)
        val loginViewModel = viewModelProivder<LoginViewModel>(loginViewModelFactory)
        val binding =
            DataBindingUtil.setContentView<ActivityLoginBinding>(this, R.layout.activity_login)
        binding.viewModel = loginViewModel
        setContentView(binding.root)

        /**
         * author : lcyer
         * content : Login Success
         **/
        loginViewModel.observeUserState.observe(this, Observer {

        })

        loginViewModel.performSignInEvent.observe(this, EventObserver { signInType ->
            if (signInType == SignInType.RequestSignIn) {
                Log.d("cylee", "SignInType.RequestSignIn")
                startActivityForResult(
                    signInHanlder.makeSignInIntent(),
                    RC_SIGN_IN
                )
            } else if (signInType == SignInType.RequestSignOut) {

            } else {

            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }
}