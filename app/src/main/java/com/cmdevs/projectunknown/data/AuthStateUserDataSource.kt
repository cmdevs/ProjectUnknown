package com.cmdevs.projectunknown.data

import androidx.lifecycle.LiveData

interface AuthStateUserDataSource {
    fun startingListener()
    fun clearListener()
    fun getObservableUserInfo(): LiveData<Result<FirebaseUserInfoBasic?>>
}