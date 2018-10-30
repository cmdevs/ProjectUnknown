package com.cmdevs.projectunknown.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cmdevs.projectunknown.data.EmailInfo
import com.cmdevs.projectunknown.result.Event
import com.cmdevs.projectunknown.util.safeLet

class LoginViewModel(
    val provider: String
) : ViewModel() {

    val emailId = MutableLiveData<String>()
    val emailPassword = MutableLiveData<String>()

    val _emailLoginEvent = MutableLiveData<Event<EmailInfo>>()
    val emailLoginEvent: LiveData<Event<EmailInfo>>
        get() = _emailLoginEvent

    val _googleSignInEvent = MutableLiveData<Event<Unit>>()
    val googleSignInEvent: LiveData<Event<Unit>>
        get() = _googleSignInEvent

    fun googleSignIn() {
        _googleSignInEvent.postValue(Event(Unit))
    }

    fun emailLogin() {
        safeLet(
            emailId.value,
            emailPassword.value
        ) { id, password ->
            _emailLoginEvent.postValue(Event(EmailInfo(id!!, password!!)))
        }

        Log.d("cylee", "emailId : ${emailId.value}")
        Log.d("cylee", "emailPassword : ${emailPassword.value}")
    }

}