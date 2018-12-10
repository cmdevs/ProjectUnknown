package com.cmdevs.projectunknown.ui

import android.content.Intent
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cmdevs.projectunknown.result.Event
import com.cmdevs.projectunknown.util.map
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class LoginViewModel(
    private val firebaseAuth: FirebaseAuth,
    signInViewModelDelegate: SignInViewModelDelegate
) : ViewModel(), SignInViewModelDelegate by signInViewModelDelegate {

    val _navigationToGoogleSignIn = MutableLiveData<Event<Unit>>()
    val navigationToGoogleSignIn: LiveData<Event<Unit>>
        get() = _navigationToGoogleSignIn

    val _navigationToFacebookSignIn = MutableLiveData<Event<Unit>>()
    val navigationToFacebookSignIn: LiveData<Event<Unit>>
        get() = _navigationToFacebookSignIn

    val facebookSignInResult: LiveData<Boolean>

    init {
        facebookSignInResult = currentAuthInfo.map {
            isSignIn()
        }
    }

    fun onGoogleSignInClicked() {
        _navigationToGoogleSignIn.postValue(Event(Unit))
    }

    fun onFacebookSignInClicked() {
        _navigationToFacebookSignIn.postValue(Event(Unit))
    }

    /*fun onSignInResult(data: Intent?) {
        Log.d("cylee", "onSignInResult")
        val task = GoogleSignIn.getSignedInAccountFromIntent(data)
        try {
            val account = task.getResult(ApiException::class.java)
            val credential = GoogleAuthProvider.getCredential(account.idToken, null)
            //val cre = FacebookAuthProvider.getCredential()
            firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener {
                    navigationToProfile.postValue(Event(it.isSuccessful))
                }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }*/
}