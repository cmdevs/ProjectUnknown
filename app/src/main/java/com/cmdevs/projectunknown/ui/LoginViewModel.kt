package com.cmdevs.projectunknown.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.cmdevs.projectunknown.data.FirebaseUserInfo
import com.cmdevs.projectunknown.domain.auth.ObserveUserAuthStateUseCase
import com.cmdevs.projectunknown.domain.result.Result

class LoginViewModel(
    observeUserAuthStateUseCase: ObserveUserAuthStateUseCase
) : ViewModel() {

    val observeUserState: LiveData<Result<FirebaseUserInfo>>

    init {
        observeUserAuthStateUseCase.execute(Unit) // Satrt Listener Google Firebase Auth State
        observeUserState = observeUserAuthStateUseCase.observe() //
    }
}