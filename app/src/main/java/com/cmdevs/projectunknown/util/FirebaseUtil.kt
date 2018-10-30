package com.cmdevs.projectunknown.util

import androidx.appcompat.app.AppCompatActivity
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.activity_login.*


fun provideTokenToFirebase(token: Any?, block: (AuthCredential) -> Unit) {
    when (token) {
        is GoogleSignInAccount -> GoogleAuthProvider.getCredential(token.idToken, null)
        is AccessToken -> FacebookAuthProvider.getCredential(token.token)
        else -> null
    }?.let {
        block(it)
    }
}

fun AppCompatActivity.setupFacebook(callbackManager: CallbackManager, block: (Any?) -> Unit) {
    with(facebookSignButton) {
        setReadPermissions("email", "public_profile")
        registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(result: LoginResult?) {
                block(result)
            }

            override fun onCancel() {
                block(Unit)
            }

            override fun onError(error: FacebookException?) {
                block(error)
            }
        })
    }
}