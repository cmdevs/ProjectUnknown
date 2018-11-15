package com.cmdevs.projectunknown.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cmdevs.projectunknown.data.EmailInfo
import com.cmdevs.projectunknown.result.Event
import com.cmdevs.projectunknown.ui.signin.SignInDelegate
import com.cmdevs.projectunknown.util.safeLet

class LoginViewModel(
    val signInDelegate: SignInDelegate
) : ViewModel(), SignInDelegate by signInDelegate {

    val emailId = MutableLiveData<String>()
    val emailPassword = MutableLiveData<String>()

    val _emailSignInTitle = MutableLiveData<String>()
    val emailSignInTitle: LiveData<String>
        get() = _emailSignInTitle

    val _emailLoginEvent = MutableLiveData<Event<EmailInfo>>()
    val emailLoginEvent: LiveData<Event<EmailInfo>>
        get() = _emailLoginEvent

    val _emailLoginInputEvent = MutableLiveData<Event<Unit>>()
    val emailLoginInputEvent: LiveData<Event<Unit>>
        get() = _emailLoginInputEvent

    val _navigationToGoogleSignIn = MutableLiveData<Event<Unit>>()
    val navigationToGoogleSignIn: LiveData<Event<Unit>>
        get() = _navigationToGoogleSignIn

    val _facebookSignInEvent = MutableLiveData<Event<Unit>>()
    val facebookSignInEvent: LiveData<Event<Unit>>
        get() = _facebookSignInEvent

    fun onGoogleSignInClicked() {
        if (isSignedIn()) { //logout

        } else { //sign In
            _navigationToGoogleSignIn.postValue(Event(Unit))
        }
    }

    fun facebookSignIn() {
        _facebookSignInEvent.postValue(Event(Unit))
    }

    fun emailLoginInput(type: String) {
        if (type.equals("join")) _emailSignInTitle.postValue("회원가입") else _emailSignInTitle.postValue(
            "로그인"
        )
        _emailLoginInputEvent.postValue(Event(Unit))
    }

    fun emailLogin() {
        safeLet(
            emailId.value,
            emailPassword.value
        ) { id, password ->
            _emailLoginEvent.postValue(Event(EmailInfo(id, password)))
        }
    }

}