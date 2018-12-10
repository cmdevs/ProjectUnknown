package com.cmdevs.projectunknown.util

import android.util.Log
import com.cmdevs.projectunknown.data.EmailInfo
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider


fun LoginManager.facebookSignIn(callbackManager: CallbackManager, block: (Any?) -> Unit) {

    registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
        override fun onSuccess(result: LoginResult?) {
            Log.d("cylee","onSuccess()")
            block(result?.accessToken)
        }

        override fun onCancel() {
            Log.d("cylee","onCancel()")
            block(null)
        }

        override fun onError(error: FacebookException?) {
            Log.d("cylee","onError()")
            block(error)
        }
    })

}


/*fun AppCompatActivity.setupFacebook(callbackManager: CallbackManager, block: (Any?) -> Unit) {
    with(facebookSignButton) {
        setReadPermissions("email", "public_profile")
        registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(result: LoginResult) {
                Log.d("cylee", "onSuccess()")
                block(result)
            }

            override fun onCancel() {
                Log.d("cylee", "onCancel()")
                block(null)
            }

            override fun onError(error: FacebookException) {
                Log.d("cylee", "onError()")
                block(error)
            }
        })
    }
}*/

fun FirebaseAuth.provideTokenToFirebase(token: Any?, block: (Task<AuthResult>) -> Unit) {
    when (token) {
        is GoogleSignInAccount -> GoogleAuthProvider.getCredential(token.idToken, null)
        is AccessToken -> FacebookAuthProvider.getCredential(token.token)
        else -> null
    }?.let {
        signInWithCredential(it)
            .addOnCompleteListener {
                block(it)
            }
    }
}

fun FirebaseAuth.registerEmail(info: EmailInfo, block: (Task<AuthResult>) -> Unit) {
    createUserWithEmailAndPassword(info.eamilId, info.emailPassword)
        .addOnCompleteListener {
            if (it.isSuccessful) {
                block(it)
            } else {
                signInEmail(info) {
                    it?.let {
                        if (it.isSuccessful) {
                            block(it)
                        }
                    }
                }
            }
        }
}

fun FirebaseAuth.signInEmail(info: EmailInfo, block: (Task<AuthResult>?) -> Unit) {
    signInWithEmailAndPassword(info.eamilId, info.emailPassword)
        .addOnCompleteListener {
            if (it.isSuccessful) block(it) else block(null)
        }
}

