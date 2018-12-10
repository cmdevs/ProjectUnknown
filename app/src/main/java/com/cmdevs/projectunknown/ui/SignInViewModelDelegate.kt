package com.cmdevs.projectunknown.ui

import androidx.lifecycle.LiveData
import com.cmdevs.projectunknown.data.UserAuthInfo
import com.cmdevs.projectunknown.domain.ObserveFacebookAcceseTokenUseCase
import com.cmdevs.projectunknown.result.Result

interface SignInViewModelDelegate {

    val currentAuthInfo: LiveData<Result<UserAuthInfo>>

    fun isSignIn(): Boolean
}

internal class FirebaseSignInViewModelDelegate(
    observeFacebookAcceseTokenUseCase: ObserveFacebookAcceseTokenUseCase
) : SignInViewModelDelegate {

    override val currentAuthInfo: LiveData<Result<UserAuthInfo>>

    init {
        currentAuthInfo = observeFacebookAcceseTokenUseCase.observe()
        observeFacebookAcceseTokenUseCase.execute(Unit)
    }

    override fun isSignIn(): Boolean {
        return (currentAuthInfo.value as? Result.Success)?.data?.isSignIn() == true
    }
}