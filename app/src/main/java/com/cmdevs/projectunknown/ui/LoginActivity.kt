package com.cmdevs.projectunknown.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.cmdevs.projectunknown.BaseActivity
import com.cmdevs.projectunknown.R
import com.cmdevs.projectunknown.data.User
import com.cmdevs.projectunknown.databinding.ActivityLoginBinding
import com.cmdevs.projectunknown.result.EventObserver
import com.cmdevs.projectunknown.ui.dialog.LoginBottomSheetDialog
import com.cmdevs.projectunknown.util.*
import com.cmdevs.projectunknown.util.signin.SignInHandler
import com.cmdevs.projectunknown.util.signin.SignInSuccess
import com.facebook.CallbackManager
import com.facebook.login.LoginManager
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import org.kodein.di.generic.instance

class LoginActivity : BaseActivity() {

    val factory by instance<LoginViewModelProviderFactory>()
    val firebaseAuth by instance<FirebaseAuth>()
    val callbackManager by instance<CallbackManager>()
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

        loginViewModel.googleSignInEvent.observe(this, EventObserver {
            /*startActivityForResult(
                Intent(GoogleSignIn.getClient(this@LoginActivity, googleSignOptions).signInIntent),
                REQUEST_GOOGLE_SIGNING
            )*/
            startActivityForResult(
                signInHandler.makeSignIntent(),
                REQUEST_GOOGLE_SIGNING
            )
        })

        loginViewModel.facebookSignInEvent.observe(this, EventObserver {
            loginManager.logInWithReadPermissions(
                this,
                mutableListOf("email", "public_profile")
            )
            loginManager.facebookSignIn(
                callbackManager
            ) {
                /*when (it) {
                    is Au -> {
                        it?.let {
                            Log.d("cylee", "success")
                            firebaseAuth.provideTokenToFirebase(it as LoginResult) {
                                *//*with(firebaseAuth) {
                                    signInWithCredential(it)
                                        .addOnCompleteListener {
                                            //just or viewmodel?
                                        }
                                }*//*
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
                }*/
            }
        })

        loginViewModel.emailLoginEvent.observe(this, EventObserver {
            lottieView.loadingStart()
            firebaseAuth.registerEmail(it) {
                //doing
                Log.d("cylee", "email it ${it}")
                it.takeIf {
                    it.isSuccessful
                }?.let {
                    it?.result?.user?.let {
                        startActivity(
                            startIntent(
                                this@LoginActivity,
                                User(
                                    it.email,
                                    it.displayName,
                                    "",
                                    it.photoUrl.toString()
                                )
                            )
                        )
                    }
                }
                lottieView.loadingStop()
            }
        })

        loginViewModel.emailLoginInputEvent.observe(this, EventObserver {
            Log.d("cylee", "emailLoginInputEvent::clicked!!")
            LoginBottomSheetShow(LoginBottomSheetDialog.newInstance()) {
                show(
                    supportFragmentManager,
                    "LoginBottomSheet"
                )
            }
        })

    }

    @SuppressLint("RestrictedApi")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        Log.d("cylee", "onActivityResult()")
        super.onActivityResult(requestCode, resultCode, data)
        /*signInHandler.signIn(resultCode, data) {
            if (it == SignInSuccess) {
                startActivity(
                    startIntent(
                        this@LoginActivity,
                        User(
                            IdpResponse.fromResultIntent(data)?.user?.email,
                            IdpResponse.fromResultIntent(data)?.user?.name,
                            "",
                            IdpResponse.fromResultIntent(data)?.user?.photoUri.toString()
                        )
                    )
                )
            }
        }*/
        /*when (requestCode) {
            REQUEST_GOOGLE_SIGNING -> {
                lottieView.loadingStart()
                GoogleSignIn.getSignedInAccountFromIntent(data).run {
                    firebaseAuth.provideTokenToFirebase(getResult(ApiException::class.java)) {
                        it.takeIf {
                            it.isSuccessful
                        }?.let {
                            it?.result?.user?.let {
                                startActivity(
                                    startIntent(
                                        this@LoginActivity,
                                        User(
                                            it.email,
                                            it.displayName,
                                            "",
                                            it.photoUrl.toString()
                                        )
                                    )
                                )
                            }
                        }
                        lottieView.loadingStop()
                    }
                }
            }
        }*/
    }

    fun provideViewModel(): LoginViewModel = viewModelProvder(factory)
}