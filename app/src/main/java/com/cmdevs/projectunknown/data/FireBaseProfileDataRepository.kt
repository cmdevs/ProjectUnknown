package com.cmdevs.projectunknown.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

open class FireBaseProfileDataRepository(
    val profileDataSource: ProfileDataSource
) {

    val _profileDataRegisterObservable = MutableLiveData<ProfileData>()
    val profileDataRegisterObservable: LiveData<ProfileData>
        get() = _profileDataRegisterObservable

    fun registerFireStoreProfile(userAuthInfo: UserAuthInfo) {
        _profileDataRegisterObservable.postValue(
            profileDataSource.registerFireStoreProfile(userAuthInfo)
        )
    }
}