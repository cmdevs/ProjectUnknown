package com.cmdevs.projectunknown.viewmodels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult

class LoginViewModel
    : ViewModel() {

    val email = ObservableField<String>()
    val password = ObservableField<String>()
    private val googleSignEvent = MutableLiveData<Void>()
    private val sendUserTaskEvent = MutableLiveData<Task<AuthResult>>()
    private val emailLoginEvent = MutableLiveData<MutableMap<String, String>>()

    fun getGoogleSignEvent() = googleSignEvent
    fun getSendUserTaskEvent() = sendUserTaskEvent

    fun googleSignClick() {
        googleSignEvent.postValue(null)
    }

    fun sendUserTask(task: Task<AuthResult>) {
        sendUserTaskEvent.value = task
    }

    fun getEmailLoginEvent() = emailLoginEvent

    fun emailLogin() {
        email.get()?.let {
            password.get()?.let {
                //val mutableMap = mutableMapOf("email" to email.get(), "password" to it)
                emailLoginEvent.postValue(mutableMapOf("email" to email.get()!!, "password" to password.get()!!))
            }
        }
    }

}
