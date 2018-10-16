package com.cmdevs.projectunknown.viewmodels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.content.Intent
import android.databinding.ObservableField
import android.util.Log
import android.widget.Toast
import com.cmdevs.projectunknown.LoginActivity
import com.cmdevs.projectunknown.data.Profile
import com.cmdevs.projectunknown.ui.more.ProfileDetailActivity
import com.cmdevs.projectunknown.util.loginException
import com.cmdevs.projectunknown.util.safeLet
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.*
import javax.inject.Inject

class LoginViewModel
@Inject constructor
    (
    private val context: Application,
    private val firebaseAuth: FirebaseAuth,
    private val googleConfig: GoogleSignInOptions,
    private val facebookManager: CallbackManager
) : AndroidViewModel(context) {

    val email = ObservableField<String>()
    val password = ObservableField<String>()

    val loadingDialogEvent = MutableLiveData<Boolean>()

    val signTitle = "sign"
    val joinTitle = "join"
    val emailTitle = ObservableField<String>()
    val emailEvent = MutableLiveData<Unit>()

    //private val emailJoin = MutableLiveData<String>()
    //private val emailSign = MutableLiveData<String>()

    private val googleSignEvent = MutableLiveData<Intent>()
    private val sendUserTaskEvent = MutableLiveData<Intent>()

    fun getGoogleSignEvent() = googleSignEvent
    fun getSendUserTaskEvent() = sendUserTaskEvent

    fun googleSignClick() {
        googleSignEvent.postValue(
            Intent(
                GoogleSignIn.getClient(
                    context,
                    googleConfig
                ).signInIntent
            )
        )
    }

    fun emailEvent(type: String) {
        if (type.equals("sign")) emailTitle.set("로그인") else emailTitle.set("회원가입")
        emailEvent.postValue(null)
    }

    fun emailLogin() {
        loadingDialogEvent.postValue(true)
        safeLet(email.get(), password.get()) { email, password ->
            emailTitle.get()?.let {
                if (it.equals("로그인")) {
                    firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener {
                            makeIntentForLogin(it)
                        }
                } else {
                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener {
                            makeIntentForLogin(it)
                        }
                }
            }
        }
    }

    fun makeIntentForLogin(task: Task<AuthResult>) {
        task.takeIf { it.isSuccessful }?.let {
            with(task.result.user) {
                val intent = Intent(
                    context, ProfileDetailActivity::class.java
                ).apply {
                    putExtra(
                        "profile",
                        Profile(email!!, displayName, null, (photoUrl.toString()))
                    )
                }
                sendUserTaskEvent.value = intent
            }
        } ?: let {
            loginException(task.exception!!) {
                Toast.makeText(context, it.message.toString(), Toast.LENGTH_SHORT).show()
                loadingDialogEvent.postValue(false)
            }
        }
    }

    fun setupFacebook() {
        LoginManager.getInstance()
            .registerCallback(facebookManager, object : FacebookCallback<LoginResult> {
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

    fun onStart() {
        firebaseAuth.currentUser.takeIf { it != null }?.let {
            Log.d("cylee", "auto login")
            //todo
            //move to mainActivity.
        }
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        facebookManager.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            LoginActivity.RC_SIGN_IN -> {
                GoogleSignIn.getSignedInAccountFromIntent(data).run {
                    provideToFirebase(getResult(ApiException::class.java))
                }
            }
        }
    }

    fun provideToFirebase(token: Any?) {
        loadingDialogEvent.postValue(true)
        val credential: AuthCredential? = when (token) {
            is GoogleSignInAccount -> GoogleAuthProvider.getCredential(token.idToken, null)
            is AccessToken -> FacebookAuthProvider.getCredential(token.token)
            else -> null
        }
        credential?.let {
            with(firebaseAuth) {
                signInWithCredential(it)
                    .addOnCompleteListener {
                        makeIntentForLogin(it)
                    }
            }
        }
    }

}
