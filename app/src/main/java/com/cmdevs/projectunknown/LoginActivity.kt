package com.cmdevs.projectunknown

import android.arch.lifecycle.Observer
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.cmdevs.projectunknown.databinding.ActivityLoginBinding
import com.cmdevs.projectunknown.util.obtainViewModel
import com.cmdevs.projectunknown.viewmodels.LoginViewModel
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {

    val clickListener = View.OnClickListener {
        when (it) {
            googleSignButton -> {
                viewModel.googleSignClick()
            }
        }
    }

    val auth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    val facebookManager: CallbackManager by lazy {
        CallbackManager.Factory.create()
    }

    lateinit var viewModel: LoginViewModel

    companion object {
        const val RC_SIGN_IN = 9001
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = obtainViewModel()
        bindView()
        setupFacebook()
        subscribeUi()
    }

    override fun onStart() {
        super.onStart()
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        facebookManager.onActivityResult(requestCode, resultCode, data) //facebook
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            RC_SIGN_IN -> {
                GoogleSignIn.getSignedInAccountFromIntent(data).run {
                    provideToFirebase(getResult(ApiException::class.java))
                }
            }
        }
    }

    fun bindView() {
        DataBindingUtil.setContentView<ActivityLoginBinding>(this, R.layout.activity_login)
            .run {
                clickListener = this@LoginActivity.clickListener
                viewmodel = this@LoginActivity.viewModel
            }
    }

    fun setupFacebook() {
        with(facebookSignButton) {
            setReadPermissions("email", "public_profile")
            registerCallback(facebookManager, object : FacebookCallback<LoginResult> {
                override fun onSuccess(result: LoginResult?) {
                    provideToFirebase(result?.accessToken)
                }

                override fun onCancel() {
                    //doSomething
                }

                override fun onError(error: FacebookException?) {
                    //doSomething
                }
            })
        }
    }

    fun googleConfig() = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(getString(R.string.default_web_client_id))
        .requestEmail()
        .build()

    fun provideToFirebase(token: Any?) {
        val credential: AuthCredential? = when (token) {
            is GoogleSignInAccount -> GoogleAuthProvider.getCredential(token.idToken, null)
            is AccessToken -> FacebookAuthProvider.getCredential(token.token)
            else -> null
        }

        credential?.let {
            auth.run {
                signInWithCredential(it)
                    .addOnCompleteListener {
                        viewModel.sendUserTask(it)
                    }
            }
        }
    }

    fun createEmail(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) viewModel.sendUserTask(it) else signEmail(
                    email,
                    password
                )
            }
    }

    fun signEmail(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { viewModel.sendUserTask(it) }
    }

    fun subscribeUi() {
        viewModel.apply {
            getGoogleSignEvent().observe(this@LoginActivity, Observer {
                GoogleSignIn.getClient(this@LoginActivity, googleConfig()).run {
                    startActivityForResult(Intent(signInIntent), RC_SIGN_IN)
                }
            })

            getEmailLoginEvent().observe(this@LoginActivity, Observer {
                it?.let {
                    createEmail(it.get("email")!!, it.get("password")!!)
                }
            })

            getSendUserTaskEvent().observe(this@LoginActivity, Observer {
                Log.d("cylee", "todo other activity send data it $it")
            })
        }
    }

    fun obtainViewModel() = obtainViewModel(LoginViewModel::class.java)

}