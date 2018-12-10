package com.cmdevs.projectunknown.util.signin

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.google.android.gms.auth.api.signin.GoogleSignInClient


sealed class SignType<out T> {
    data class Google<T>(val data: T) : SignType<T>()
    //data class Facebook<T>(val data: T) : SignType<T>()
}

interface SignInHandler {
    fun <T> makeSignIntent(type: SignType<T>): Intent?
    fun signIn(resultCode: Int, data: Intent?, onComplete: (SignInResult) -> Unit)
    fun signOut(context: Context, onComplete: () -> Unit = {})
}

class DefaultSignInHandler : SignInHandler {
    override fun <T> makeSignIntent(type: SignType<T>): Intent? {
        return when (type) {
            is SignType.Google -> {
                (type.data as GoogleSignInClient).signInIntent
            }
        }
    }

    override fun signIn(resultCode: Int, data: Intent?, onComplete: (SignInResult) -> Unit) {
        when (resultCode) {
            Activity.RESULT_OK -> onComplete(SignInSuccess)
            //else -> onComplete(SignInFailed(null))
        }

    }

    override fun signOut(context: Context, onComplete: () -> Unit) {
//        AuthUI.getInstance()
//            .signOut(context)
//            .addOnCompleteListener { onComplete() }
    }

}
