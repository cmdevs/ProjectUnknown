package com.cmdevs.projectunknown.domain

import com.cmdevs.projectunknown.data.EmailUserInfo
import com.cmdevs.projectunknown.data.SignInStateDataSource
import com.cmdevs.projectunknown.data.UserAuthInfo

class ObserveEmailAuthStateUseCase(
    private val firebaseSignInEmailStateDataSource: SignInStateDataSource
) : MediatorUseCase<EmailUserInfo, UserAuthInfo>() {

    val currentUser = firebaseSignInEmailStateDataSource.observe()

    init {
        result.addSource(currentUser) {
            result.postValue(it)
        }
    }

    override fun execute(parameters: EmailUserInfo) {
        firebaseSignInEmailStateDataSource.requestSignIn(parameters)
    }
}