package com.cmdevs.projectunknown.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.cmdevs.projectunknown.BaseActivity
import com.cmdevs.projectunknown.R
import com.cmdevs.projectunknown.data.User
import com.cmdevs.projectunknown.databinding.ActivityLoginBinding
import com.cmdevs.projectunknown.result.EventObserver
import com.cmdevs.projectunknown.util.signin.SignInHandler
import com.cmdevs.projectunknown.util.signin.SignInSuccess
import com.cmdevs.projectunknown.util.signin.SignType
import com.cmdevs.projectunknown.util.viewModelProvder
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import org.kodein.di.generic.instance

class LoginActivity : BaseActivity() {

    val factory by instance<LoginViewModelProviderFactory>()
    val googleSignInClient by instance<GoogleSignInClient>()
    val facebookCallbackManager by instance<CallbackManager>()
    val loginManager by instance<LoginManager>()
    val signInHandler by instance<SignInHandler>()

    lateinit var loginViewModel: LoginViewModel

    companion object {
        const val REQUEST_GOOGLE_SIGNING = 1000
        fun startIntent(context: Context, user: User): Intent {
            return Intent(context, ProfileActivity::class.java).apply {
                putExtra("user", user)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginViewModel = provideViewModel()

        DataBindingUtil.setContentView<ActivityLoginBinding>(
            this,
            R.layout.activity_login
        ).run {
            viewmodel = loginViewModel
        }

        loginViewModel.navigationToGoogleSignIn.observe(this, EventObserver {
            signInHandler.makeSignIntent(
                SignType.Google(googleSignInClient)
            )?.let { startActivityForResult(it, REQUEST_GOOGLE_SIGNING) }
        })

        loginViewModel.navigationToFacebookSignIn.observe(this, EventObserver {
            loginManager.logInWithReadPermissions(
                this,
                mutableListOf("public_profile","email")
            )
        })

        loginViewModel.facebookSignInResult.observe(this, Observer {
            Log.d("cylee","facebook signIn result : ${it}")
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d("cylee","onActivityResult")
        facebookCallbackManager.onActivityResult(requestCode, resultCode, data)
        /*signInHandler.signIn(resultCode, data) {
            it.takeIf { it == SignInSuccess }?.let { loginViewModel.onSignInResult(data) }
        }*/
    }

    fun provideViewModel(): LoginViewModel = viewModelProvder(factory)
}