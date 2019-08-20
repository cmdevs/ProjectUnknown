package com.cmdevs.projectunknown.data

import androidx.lifecycle.LiveData
import com.cmdevs.projectunknown.domain.result.Result

interface AuthStateUserDataSource {
    fun startingListener()
    fun clearListener()
    fun getObservableUserInfo(): LiveData<Result<FirebaseUserInfoBasic?>>
}