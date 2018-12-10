package com.cmdevs.projectunknown.data

import androidx.lifecycle.LiveData
import com.cmdevs.projectunknown.result.Result

interface AccessTokenStateDataSource {

    fun triggerListener()
    fun getAuthInfo(): LiveData<Result<UserAuthInfo>>
}

