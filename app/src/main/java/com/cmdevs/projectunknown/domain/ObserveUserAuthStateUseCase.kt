package com.cmdevs.projectunknown.domain

import com.cmdevs.projectunknown.data.SignInStateDataSource
import com.cmdevs.projectunknown.data.UserAuthInfo

class ObserveUserAuthStateUseCase(
    private val signInStateDataSource: SignInStateDataSource
) : MediatorUseCase<Unit, UserAuthInfo>() {

    val currentUser = signInStateDataSource.observe()

    init {
        result.addSource(currentUser) {
            //signin
            result.postValue(it)
        }
    }

    override fun execute(parameters: Unit) {
        signInStateDataSource.triggerListener()
    }
}
