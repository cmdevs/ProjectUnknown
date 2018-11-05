package com.cmdevs.projectunknown.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.cmdevs.projectunknown.BaseActivity
import com.cmdevs.projectunknown.R
import com.cmdevs.projectunknown.databinding.ActivityLoginBinding
import com.cmdevs.projectunknown.result.EventObserver
import com.cmdevs.projectunknown.util.*
import com.facebook.CallbackManager
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import org.kodein.di.generic.instance

class LoginActivity : BaseActivity() {

    val factory by instance<LoginViewModelProviderFactory>()
    val firebaseAuth by instance<FirebaseAuth>()
    val googleSignOptions by instance<GoogleSignInOptions>()
    val callbackManager by instance<CallbackManager>()

    lateinit var loginViewModel: LoginViewModel

    companion object {
        const val REQUEST_GOOGLE_SIGNING = 1000
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginViewModel = viewModelProvder(factory)

        DataBindingUtil.setContentView<ActivityLoginBinding>(this, R.layout.activity_login).run {
            viewmodel = loginViewModel
        }

        setupFacebook(callbackManager) {
            when (it) {
                is LoginResult -> {
                    it?.let {
                        Log.d("cylee", "success")
                        firebaseAuth.provideTokenToFirebase(it as LoginResult) {
                            /*with(firebaseAuth) {
                                signInWithCredential(it)
                                    .addOnCompleteListener {
                                        //just or viewmodel?
                                    }
                            }*/
                        }
                    }
                }
                is FacebookException -> {
                    //doingSomething Exception
                    Log.d("cylee", "FacebookException")
                }
                else -> {
                    if (it == null) Log.d("cylee", "it == null")
                }
            }
        }

        loginViewModel.googleSignInEvent.observe(this, EventObserver {
            startActivityForResult(
                Intent(GoogleSignIn.getClient(this@LoginActivity, googleSignOptions).signInIntent),
                REQUEST_GOOGLE_SIGNING
            )
        })

        loginViewModel.emailLoginEvent.observe(this, EventObserver {
            lottieView.loadingStart()
            firebaseAuth.registerEmail(it) {
                //doing
                Log.d("cylee","email it ${it}")
                lottieView.loadingStop()
            }
        })

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data) //facebook
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            REQUEST_GOOGLE_SIGNING -> {
                lottieView.loadingStart()
                GoogleSignIn.getSignedInAccountFromIntent(data).run {
                    firebaseAuth.provideTokenToFirebase(getResult(ApiException::class.java)) {
                        //just or viewmodel?
                        Log.d("cylee", "it ${it}")
                        lottieView.loadingStop()
                    }
                }
            }
        }
    }

}