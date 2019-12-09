package com.cmdevs.projectunknown.domain.profile

import com.cmdevs.projectunknown.data.ProfileData
import com.cmdevs.projectunknown.data.UserDataSource
import com.cmdevs.projectunknown.domain.MediatorUseCase
import com.cmdevs.projectunknown.domain.result.Result
import timber.log.Timber

class ObserveProfileUserUseCase(
        val firebaseUserDataSource: UserDataSource
) : MediatorUseCase<String, ProfileData>() {

    val profileUserObservable = firebaseUserDataSource.observeUser()

    init {
        result.addSource(profileUserObservable) {
            val profileData: ProfileData? = (it as? Result.Success<ProfileData?>)?.data
            profileData?.let {
                result.postValue(Result.Success(it))
            } ?: let {
                Timber.d("ProfileData null")
                result.postValue(Result.Error(Exception("ProfileData null")))
            }
        }
    }

    override fun execute(parameters: String) {
        firebaseUserDataSource.getUser(parameters)
    }
}