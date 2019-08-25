package com.cmdevs.projectunknown.domain.auth

import com.cmdevs.projectunknown.data.AuthStateUserDataSource
import com.cmdevs.projectunknown.data.FirebaseRegisteredUserInfo
import com.cmdevs.projectunknown.data.FirebaseUserInfo
import com.cmdevs.projectunknown.data.FirebaseUserInfoBasic
import com.cmdevs.projectunknown.domain.MediatorUseCase
import com.cmdevs.projectunknown.domain.result.Result

class ObserveUserAuthStateUseCase(
    val firebaseAuthStateUserDataSource: AuthStateUserDataSource
) : MediatorUseCase<Any, FirebaseUserInfo>() {

    val firebaseUserObservable = firebaseAuthStateUserDataSource.getObservableUserInfo()

    init {
        result.addSource(firebaseUserObservable) {
            val firebaseUserInfoBasic = (it as? Result.Success)?.data
            firebaseUserInfoBasic?.let { firebaseUserInfo ->
                updateUserObserve(firebaseUserInfo)
            } ?: let {
                //Result.Error 처리

            }
        }
    }

    fun updateUserObserve(firebaseUserInfo: FirebaseUserInfoBasic) {

        /**
         * UesrInfo isRegistered 처리
         **/
        result.postValue(
            Result.Success(
                FirebaseRegisteredUserInfo(firebaseUserInfo, false)
            )
        )
    }

    override fun execute(parameters: Any) {
        firebaseAuthStateUserDataSource.startingListener()
    }
}