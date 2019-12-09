package com.cmdevs.projectunknown.util

import android.content.Context
import android.content.Intent
import com.firebase.ui.auth.AuthUI
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

interface SignInHandler {
    fun makeSignInIntent(): Intent?
    fun signIn(resultCode: Int, data: Intent?, onComplete: (SignInResult) -> Unit)
    fun signOut(context: Context, onComplete: () -> Unit = {})
}

class DefaultSignInHanlder : SignInHandler {

    override fun makeSignInIntent(): Intent? {
        val providers = mutableListOf(
            AuthUI.IdpConfig.GoogleBuilder().setSignInOptions(
                GoogleSignInOptions.Builder()
                    .requestId()
                    .requestEmail()
                    .requestProfile()
                    .build()
            ).build()
        )
        return AuthUI.getInstance().createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .setIsSmartLockEnabled(false)
            .build()
    }

    override fun signIn(resultCode: Int, data: Intent?, onComplete: (SignInResult) -> Unit) {

    }

    override fun signOut(context: Context, onComplete: () -> Unit) {

    }
}