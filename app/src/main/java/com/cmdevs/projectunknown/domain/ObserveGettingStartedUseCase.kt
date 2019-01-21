package com.cmdevs.projectunknown.domain

import com.cmdevs.projectunknown.data.FireBaseProfileDataRepository
import com.cmdevs.projectunknown.data.UserAuthInfo
import com.cmdevs.projectunknown.result.Result

class ObserveGettingStartedUseCase(
    val fireBaseProfileDataRepository: FireBaseProfileDataRepository
) : MediatorUseCase<UserAuthInfo, Boolean>() {

    init {
        result.addSource(fireBaseProfileDataRepository.profileDataRegisterObservable) { profile ->
            result.postValue(
                Result.Success(profile.userAuthInfo.getUid() != null)
            )
        }
    }

    override fun execute(parameters: UserAuthInfo) {
        fireBaseProfileDataRepository.registerFireStoreProfile(parameters)
    }
}

