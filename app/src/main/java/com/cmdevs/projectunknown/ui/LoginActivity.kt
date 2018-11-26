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
import com.cmdevs.projectunknown.ui.signin.SignInEvent
import com.cmdevs.projectunknown.util.signin.SignInHandler
import com.cmdevs.projectunknown.util.viewModelProvder
import org.kodein.di.generic.instance

class LoginActivity : BaseActivity() {

    val factory by instance<LoginViewModelProviderFactory>()
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

        loginViewModel.performSignInEvent.observe(this, EventObserver {
            /*if (it == SignInEvent.RequestSignIn) {
                signInHandler.makeSignIntent()?.let {
                    startActivityForResult(it, REQUEST_GOOGLE_SIGNING)
                }
            }*/

            when (it) {
                SignInEvent.RequestSignIn -> {
                    signInHandler.makeSignIntent()?.let {
                        Log.d("cylee","sign in")
                        startActivityForResult(it, REQUEST_GOOGLE_SIGNING)
                    }
                }
                SignInEvent.RequestSignOut -> {
                    Log.d("cylee","sign out")
                    signInHandler.signOut(this@LoginActivity)
                }
            }
        })

        loginViewModel.currentSession.observe(this, Observer {
            Log.d("cylee", "currentSession : ${it}")
        })

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }

    fun provideViewModel(): LoginViewModel = viewModelProvder(factory)
}