package com.cmdevs.projectunknown.domain

import android.util.Log
import com.cmdevs.projectunknown.data.AccessTokenStateDataSource
import com.cmdevs.projectunknown.data.UserAuthInfo


open class ObserveFacebookAcceseTokenUseCase(
    private val accessTokenStateDataSource: AccessTokenStateDataSource
) : MediatorUseCase<Unit, UserAuthInfo>() {

    private val currentFacebookAuthInfo = accessTokenStateDataSource.getAuthInfo()

    init {
        result.addSource(currentFacebookAuthInfo) {
            result.postValue(it)
        }
    }

    override fun execute(parameters: Unit) {
        accessTokenStateDataSource.triggerListener()
    }
}