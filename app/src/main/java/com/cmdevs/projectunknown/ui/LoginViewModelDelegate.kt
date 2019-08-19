package com.cmdevs.projectunknown.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cmdevs.projectunknown.data.FirebaseUserInfo
import com.cmdevs.projectunknown.domain.auth.ObserveUserAuthStateUseCase
import com.cmdevs.projectunknown.domain.result.Event
import com.cmdevs.projectunknown.domain.result.Result

enum class SignInType {
    RequestSignIn, RequestSignOut
}

interface LoginViewModelDelegate {

    val performSignInEvent: MutableLiveData<Event<SignInType>>
    val observeUserState: LiveData<Result<FirebaseUserInfo>>

    fun emitSignInRequest()
    fun emitSignOutRequest()
}


internal class FirebaseLoginViewModelDelegate(
    observeUserAuthStateUseCase: ObserveUserAuthStateUseCase
) : LoginViewModelDelegate {

    override val performSignInEvent = MutableLiveData<Event<SignInType>>()
    override val observeUserState: LiveData<Result<FirebaseUserInfo>>

    init {
        observeUserState = observeUserAuthStateUseCase.observe()
        observeUserAuthStateUseCase.execute(Unit) //Firebase trigger SignInEvent Listener
    }

    override fun emitSignInRequest() {
        performSignInEvent.postValue(Event(SignInType.RequestSignIn))
    }

    override fun emitSignOutRequest() {
        performSignInEvent.postValue(Event(SignInType.RequestSignOut))
    }
}