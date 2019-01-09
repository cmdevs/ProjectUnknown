package com.cmdevs.projectunknown.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cmdevs.projectunknown.data.UserAuthInfo
import com.cmdevs.projectunknown.result.Event
import com.cmdevs.projectunknown.result.Result
import com.cmdevs.projectunknown.util.map
import com.cmdevs.projectunknown.util.signin.SignInEvent

class LoginViewModel(
    signInViewModelDelegate: SignInViewModelDelegate
) : ViewModel(), SignInViewModelDelegate by signInViewModelDelegate {

    val isLoading: LiveData<Boolean>

    val _errorMessage = MutableLiveData<Event<Exception>>()
    val errorMessage: LiveData<Event<Exception>>
        get() = _errorMessage

    val currentAuthUser = MediatorLiveData<Event<UserAuthInfo?>>()

    init {

        currentAuthUser.addSource(currentUser) {
            if (isSignIn()) {
                currentAuthUser.postValue(Event((it as? Result.Success)?.data))
            } else {
                if (it is Result.Error) {
                    it.exception?.let {
                        _errorMessage.postValue(Event(it))
                    }
                }
            }
        }

        currentAuthUser.addSource(currentEmailUser) {
            if (isSignIn()) {
                currentAuthUser.postValue(Event((it as? Result.Success)?.data))
            } else {
                if (it is Result.Error) {
                    it.exception?.let {
                        _errorMessage.postValue(Event(it))
                    }
                }
            }
        }

        isLoading = currentEmailUser.map {
            Log.d("cylee","isLoading ${it == Result.Loading}")
            it == Result.Loading
        }
    }

    fun onGoogleSignInClicked() {
        requestSignIn(
            Event(SignInEvent.GOOGLE)
        )
    }

    fun onFacebookSignInClicked() {
        requestSignIn(
            Event(SignInEvent.FACEBOOK)
        )
    }

    fun onEmailSignInClicked() {
        requestSignIn(
            Event(SignInEvent.EMAIL_SIGN_IN)
        )
    }

    fun onEmailJoinInClicked() {
        requestSignIn(
            Event(SignInEvent.EMAIL_JOIN_IN)
        )
    }

    fun emailSignInRequest() {
        requestEmailSignIn()
    }

    fun emailJoinInRequest() {
        requestEmailJoinIn()
    }
}