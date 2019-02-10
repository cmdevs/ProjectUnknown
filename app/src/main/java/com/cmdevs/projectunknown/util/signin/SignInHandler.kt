package com.cmdevs.projectunknown.util.signin

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.fragment.app.DialogFragment
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.android.gms.auth.api.signin.GoogleSignInOptions


/*sealed class SignType<out T> {
    data class Google<T>(val data: T) : SignType<T>()
    //data class Facebook<T>(val data: T) : SignType<T>()
}*/

enum class SignInEvent {
    GOOGLE, FACEBOOK, EMAIL_SIGN_IN, EMAIL_JOIN_IN
}

enum class EmailEvent {
    SIGN_IN, JOIN_IN
}



interface SignInHandler {
    fun makeSignIntent(type: SignInEvent): Intent?
    fun onViewCreateEmailSignIn(dialog: DialogFragment, func: DialogFragment.() -> Unit)
    fun signIn(resultCode: Int, data: Intent?, onComplete: (SignInResult) -> Unit)
    fun signOut(context: Context, onComplete: () -> Unit = {})
}

class DefaultSignInHandler : SignInHandler {

    override fun makeSignIntent(type: SignInEvent): Intent? = when (type) {
        SignInEvent.GOOGLE -> {
            val providers = mutableListOf(
                AuthUI.IdpConfig.GoogleBuilder().setSignInOptions(
                    GoogleSignInOptions.Builder()
                        .requestId()
                        .requestEmail()
                        .build()
                ).build()
            )
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .build()
        }
        SignInEvent.FACEBOOK -> {
            val providers = mutableListOf(
                AuthUI.IdpConfig.FacebookBuilder()
                    .setPermissions(
                        mutableListOf("public_profile", "email")
                    ).build()
            )
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .build()
        }

        SignInEvent.EMAIL_SIGN_IN -> {
            null
        }

        SignInEvent.EMAIL_JOIN_IN -> {
            null
        }
    }

    override fun signIn(resultCode: Int, data: Intent?, onComplete: (SignInResult) -> Unit) {
        when (resultCode) {
            Activity.RESULT_OK -> onComplete(SignInSuccess)
            else -> onComplete(SignInFailed(IdpResponse.fromResultIntent(data)?.error))
        }
    }

    override fun signOut(context: Context, onComplete: () -> Unit) {
        AuthUI.getInstance()
            .signOut(context)
            .addOnCompleteListener { onComplete() }
    }

    override fun onViewCreateEmailSignIn(dialog: DialogFragment, func: DialogFragment.() -> Unit) {
        dialog.func()
    }
}
