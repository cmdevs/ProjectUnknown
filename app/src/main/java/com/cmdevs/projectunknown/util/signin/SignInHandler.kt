package com.cmdevs.projectunknown.util.signin

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

interface SignInHandler {
    fun makeSignIntent(): Intent?
    fun signIn(resultCode: Int, data: Intent?, onComplete: (SignInResult) -> Unit)
    fun signOut(context: Context, onComplete: () -> Unit = {})
}

class DefaultSignInHandler : SignInHandler {

    override fun makeSignIntent(): Intent? {
        val providers = mutableListOf(
            AuthUI.IdpConfig.GoogleBuilder().setSignInOptions(
                GoogleSignInOptions.Builder()
                    .requestId()
                    .requestEmail()
                    .build()
            ).build()
        )
        return AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .build()
    }

    override fun signIn(resultCode: Int, data: Intent?, onComplete: (SignInResult) -> Unit) {
        when (resultCode) {
            Activity.RESULT_OK -> onComplete(SignInSuccess)
            else -> onComplete(SignInFailed(IdpResponse.fromResultIntent(data)?.error))
        }

    }

    override fun signOut(context: Context, onComplete: () -> Unit) {

    }
}
